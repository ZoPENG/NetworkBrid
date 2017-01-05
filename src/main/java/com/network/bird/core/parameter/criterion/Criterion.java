package com.network.bird.core.parameter.criterion;

import com.network.bird.common.exception.BaseException;
import com.network.bird.core.parameter.Parameter;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public abstract class Criterion {
    /**
     * @return 查询条件中属性的名称
     */
    public abstract List<String> getPropertyName();

    /**
     * @param clazz 参数类
     * @return 查询条件中属性的值
     */
    public abstract List<Object> getPropertyValue(Class<? extends Parameter> clazz);

    /**
     * 获得字符串格式的查询条件，其中属性的名称会以%s替换，属性的值会以'?'替换
     *
     * @return 字符串格式的查询条件
     */
    public abstract String getQuery();

    /**
     * 根据属性的类型，将属性的值转换为合适的类型
     *
     * @param clazz         参数类
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 属性的值
     */
    Object getPropertyValue(Class<? extends Parameter> clazz, String propertyName, Object propertyValue) {
        Object result;
        Field field = FieldUtils.getField(clazz, propertyName, true);
        if (field == null) {
            throw new BaseException(clazz.getName() + "不存在属性" + propertyName);
        }
        Class<?> fieldType = field.getType();
        if (fieldType == propertyValue.getClass() && !fieldType.isEnum()) {
            result = propertyValue;
        } else if (propertyValue.getClass() == String.class) {
            if (fieldType == Date.class || fieldType == java.sql.Date.class || fieldType == Timestamp.class) {
                try {
                    result = DateUtils.parseDate((String) propertyValue, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
                } catch (ParseException e) {
                    throw new BaseException("只支持以下日期格式:yyyy-MM-dd,yyyy-MM-dd HH:mm:ss");
                }
            } else {
                result = propertyValue;
            }
        } else {
            result = propertyValue.toString();
        }
        return result;
    }

}
