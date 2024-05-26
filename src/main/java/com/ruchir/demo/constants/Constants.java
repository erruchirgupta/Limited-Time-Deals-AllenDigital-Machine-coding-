package com.ruchir.demo.constants;

import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.regex.Pattern;

@UtilityClass
public class Constants {
    public static final Set<String> excludeRequestLogging = Set.of("/actuator", "/api-docs", "/swagger-ui");

    public static final String[] requestLoggingPatterns = new String[]{
            "/v1/*",
            "/v2/*",
            "/api/v1/*",
            "/api/v2/*",
            "/private/*",
            "/internal/*"
    };
    public final String MDC_CORRELATION_ID = "correlationId";
    public final String CORRELATION_ID = "x-correlation-id";
    public final String AUTH_HEADER = "authorization";
    public final String MDC_TRIGGERED_BY = "triggeredBy";

}
