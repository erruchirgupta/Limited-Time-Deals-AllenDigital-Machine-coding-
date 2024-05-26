package com.ruchir.demo.utils;

import com.ruchir.demo.exception.ValidationException;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@UtilityClass
public class ValidationHelper {

    public void isNull(Object object, String errorMessage) {
        if (Objects.nonNull(object)) {
            throw new ValidationException(errorMessage);
        }
    }

    public void notNull(Object object, String errorMessage) {
        if (Objects.isNull(object)) {
            throw new ValidationException(errorMessage);
        }
    }

    public void notEmpty(Collection<?> collection, String errorMessage) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ValidationException(errorMessage);
        }
    }

    public void notEmpty(Map<?, ?> map, String errorMessage) {
        if (CollectionUtils.isEmpty(map)) {
            throw new ValidationException(errorMessage);
        }
    }

    public void notEmptyOrBlank(String value, String errorMessage) {
        if (ObjectUtils.isEmpty(value) || ObjectUtils.isEmpty(value.trim())) {
            throw new ValidationException(errorMessage);
        }
    }

    public void isTrue(Boolean value, String errorMessage) {
        if (!value) {
            throw new ValidationException(errorMessage);
        }
    }

    public void isTrueIfPreTrue(Boolean preCondition, Boolean value, String errorMessage) {
        if (preCondition) {
            isTrue(value, errorMessage);
        }
    }

    public void isNotZero(Long value, String errorMessage) {
        if (value == null || value == 0L) {
            throw new ValidationException(errorMessage);
        }
    }

    public void isNotZero(Integer value, String errorMessage) {
        if (value == null || value == 0) {
            throw new ValidationException(errorMessage);
        }
    }
}
