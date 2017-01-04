package com.network.bird.core.dao;

import java.security.InvalidParameterException;

/**
 * 定义一些通用的dao层方法
 * Created by zhoupeng on 2017/1/4.
 */
public interface IBaseDao<T> {

    /**
     * 将session缓存的内容保存到数据库，并清除session缓存
     */
    void clearSession();

    /**
     * 删除数据
     * @param entity 实体
     */
    void delete(T entity);

    /**
     * 删除数据
     * @param id 主键
     */
    void delete(String id);

    T getById(String id) throws InvalidParameterException;
}
