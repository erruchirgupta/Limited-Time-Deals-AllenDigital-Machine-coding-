package com.ruchir.demo.config;

import com.ruchir.demo.constants.Constants;
import com.ruchir.demo.utils.MDCUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component("mdcRequestFilter")
public class MDCFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("--------------------------------------------------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        MDC.clear();
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Map<String, String> headerNamesMap = MDCUtils.getCaseInsensitiveHeaderNames(httpRequest);
        MDC.put(Constants.MDC_CORRELATION_ID, MDCUtils.getCorrelationId(httpRequest, headerNamesMap));
        MDC.put(Constants.AUTH_HEADER, getHeader(httpRequest, headerNamesMap, Constants.AUTH_HEADER));
        StopWatch sw = new StopWatch();
        sw.start();
        filterChain.doFilter(servletRequest, servletResponse);
        sw.stop();
        log.info("Time taken by API call in MilliSeconds :: {}", sw.getTotalTimeMillis());
    }

    private String getHeader(HttpServletRequest httpRequest, Map<String, String> headerNamesMap, String header) {
        return getHeaderOrDefault(httpRequest, headerNamesMap, header, null);
    }

    private String getHeaderOrDefault(HttpServletRequest httpRequest, Map<String, String> headerNamesMap, String header, @Nullable String defaultValue) {
        String headerValue = headerNamesMap.containsKey(header) ? httpRequest.getHeader(headerNamesMap.get(header)) : defaultValue;
        return StringUtils.hasText(headerValue) ? headerValue : defaultValue;
    }

    @Override
    public void destroy() {
        log.info("----------------------------------------------------------");
    }
}
