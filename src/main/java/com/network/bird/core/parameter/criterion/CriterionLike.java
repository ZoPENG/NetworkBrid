package com.network.bird.core.parameter.criterion;

import com.network.bird.core.parameter.Parameter;

import java.util.Collections;
import java.util.List;


/**
 * 模糊匹配
 */
public class CriterionLike extends Criterion {
    private String propertyName;
    private Object value;

    CriterionLike(String propertynName, Object value) {
        this.propertyName = propertynName;
        this.value = value;
    }

    @Override
    public List<String> getPropertyName() {
        return Collections.singletonList(propertyName);
    }

    @Override
    public List<Object> getPropertyValue(Class<? extends Parameter> clazz) {
        Object result = getPropertyValue(clazz, propertyName, value);
        return Collections.singletonList(result);
    }

    @Override
    public String getQuery() {
        return "%s like ?";
    }

}
