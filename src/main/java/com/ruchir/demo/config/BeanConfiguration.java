package com.ruchir.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ruchir.demo.constants.Constants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Demo Service", version = "v1", license = @License(name = "Demo Internal")),
        servers = { @Server(url = "${server.appUrl}") }
)
public class BeanConfiguration {

    @Bean
    public FilterRegistrationBean<Filter> mdcFilterRegistration(@Qualifier("mdcRequestFilter") Filter mdcRequestFilter) {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(mdcRequestFilter);
        registration.addUrlPatterns(Constants.requestLoggingPatterns);
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public CustomRequestLoggingFilter requestLoggingFilter() {
        CustomRequestLoggingFilter loggingFilter = new CustomRequestLoggingFilter();
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setMaxPayloadLength(100000);
        loggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
        FilterRegistrationBean<CustomRequestLoggingFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(loggingFilter);
        registration.addUrlPatterns(Constants.requestLoggingPatterns);
        registration.setOrder(1);
        return loggingFilter;
    }

    @Bean
    @Primary
    public ObjectMapper getObjectMapper() {
        var objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public CharsetEncoder getCharsetEncoder() {
        var charset = StandardCharsets.UTF_8;
        return charset.newEncoder();
    }
}
