package com.ruchir.demo.utils;

import com.ruchir.demo.constants.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.slf4j.MDC;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@UtilityClass
public class MDCUtils {

    public static void put(String key, String value) {
        if(StringUtils.hasText(key) && StringUtils.hasText(value)){
            MDC.put(key, value);
        }
    }

    public Map<String, String> getCaseInsensitiveHeaderNames(HttpServletRequest httpRequest) {
        Map<String, String> headerNamesMap = new HashMap<>();
        Enumeration<String> currentHeaders = httpRequest.getHeaderNames();
        while (currentHeaders.hasMoreElements()) {
            String name = currentHeaders.nextElement();
            headerNamesMap.put(name.toLowerCase(), name);
        }
        return headerNamesMap;
    }

    public String getCorrelationId(HttpServletRequest httpRequest, Map<String, String> headerNamesMap) {
        String correlationId = UUID.randomUUID().toString();
        if(Objects.nonNull(httpRequest) && !CollectionUtils.isEmpty(headerNamesMap) && isCorrelationIdExist(headerNamesMap.get(Constants.CORRELATION_ID))) {
            correlationId = httpRequest.getHeader(headerNamesMap.get(Constants.CORRELATION_ID));
        }
        return correlationId;
    }

    private boolean isCorrelationIdExist(String correlationId) {
        return StringUtils.hasText(correlationId);
    }

    public String getOrGenerateCorrelationId(String correlationId) {
        return StringUtils.hasText(correlationId) ? correlationId : UUID.randomUUID().toString();
    }

    public String getCorrelationId() {
        return MDC.get(Constants.MDC_CORRELATION_ID);
    }

    public void setCorrelationId(String correlationId) {
        MDC.put(Constants.MDC_CORRELATION_ID, getOrGenerateCorrelationId(correlationId));
    }
}
