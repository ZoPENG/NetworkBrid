package com.network.bird.core.parameter.criterion;

import com.network.bird.core.parameter.Parameter;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 范围之中
 */
public class CriterionIn extends Criterion {
    private String propertyName;
    private Collection<?> values;

    CriterionIn(String propertyName, Collection<?> values) {
        this.propertyName = propertyName;
        this.values = values;
    }

    @Override
    public List<String> getPropertyName() {
        return Collections.singletonList(propertyName);
    }

    @Override
    public List<Object> getPropertyValue(Class<? extends Parameter> clazz) {
        List<Object> result = new LinkedList<>();
        for (Object value : values) {
            Object obj = getPropertyValue(clazz, propertyName, value);
            result.add(obj);
        }
        return result;
    }

    @Override
    public String getQuery() {
        StringBuilder sb = new StringBuilder("%s in (");
        for (int i = 0; i < values.size(); i++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

}
