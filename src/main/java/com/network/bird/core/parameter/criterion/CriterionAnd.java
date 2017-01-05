package com.network.bird.core.parameter.criterion;

import com.network.bird.core.parameter.Parameter;

import java.util.LinkedList;
import java.util.List;

/**
 * 逻辑与
 */
public class CriterionAnd extends Criterion {
    private Criterion[] criterions;

    CriterionAnd(Criterion... criterions) {
        this.criterions = criterions;
    }

    @Override
    public List<String> getPropertyName() {
        List<String> list = new LinkedList<>();
        for (Criterion c : criterions) {
            list.addAll(c.getPropertyName());
        }
        return list;
    }

    @Override
    public List<Object> getPropertyValue(Class<? extends Parameter> clazz) {
        List<Object> list = new LinkedList<>();
        for (Criterion c : criterions) {
            list.addAll(c.getPropertyValue(clazz));
        }
        return list;
    }

    @Override
    public String getQuery() {
        StringBuilder result = new StringBuilder();
        for (Criterion c : criterions) {
            if (c instanceof CriterionOr || c instanceof CriterionAnd) {
                result.append("(");
            }
            result.append(c.getQuery());
            if (c instanceof CriterionOr || c instanceof CriterionAnd) {
                result.append(")");
            }
            result.append(" and ");
        }
        result.delete(result.length() - 5, result.length());
        return result.toString();
    }

}
