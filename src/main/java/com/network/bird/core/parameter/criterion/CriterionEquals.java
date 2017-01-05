package com.network.bird.core.parameter.criterion;

import com.network.bird.core.parameter.Parameter;

import java.util.Collections;
import java.util.List;

/**
 * 等于
 */
public class CriterionEquals extends Criterion {
	private String propertyName;
	private Object value;

	CriterionEquals(String propertyName, Object value) {
		this.propertyName = propertyName;
		this.value = value;
	}

	@Override
	public List<String> getPropertyName() {
		return Collections.singletonList(propertyName);
	}

	@Override
	public String getQuery() {
		return "%s = ?";
	}

	@Override
	public List<Object> getPropertyValue(Class<? extends Parameter> clazz) {
		Object result = getPropertyValue(clazz,propertyName,value);
		return Collections.singletonList(result);
	}

}
