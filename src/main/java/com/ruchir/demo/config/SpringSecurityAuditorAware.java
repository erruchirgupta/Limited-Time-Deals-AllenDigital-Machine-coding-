package com.ruchir.demo.config;

import com.ruchir.demo.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String triggeredBy = MDC.get(Constants.MDC_TRIGGERED_BY);
        return Optional.of(StringUtils.isBlank(triggeredBy)? "system" : triggeredBy);
    }
}