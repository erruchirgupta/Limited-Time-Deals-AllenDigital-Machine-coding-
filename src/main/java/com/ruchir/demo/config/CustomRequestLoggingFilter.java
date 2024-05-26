package com.ruchir.demo.config;

import com.ruchir.demo.constants.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

public class CustomRequestLoggingFilter extends AbstractRequestLoggingFilter {

    @Override
    protected boolean shouldLog(@NotNull HttpServletRequest request) {
        if(Constants.excludeRequestLogging.stream().anyMatch(pattern -> request.getRequestURI().contains(pattern))) {
            return false;
        }
        return logger.isDebugEnabled();
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        logger.debug(message);
    }
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        logger.debug(message);
    }
}
