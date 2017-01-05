package com.network.bird.core.dao.impl;

import com.network.bird.core.dao.IBaseDao;
import com.network.bird.core.parameter.Parameter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * 定义一些通用的DAO层方法
 *
 * @param <T> 实现该接口时需要指定具体的实体类
 *            Created by zhoupeng on 2017/1/4.
 */
@SuppressWarnings("unchecked")
public abstract class BaseDao<T> implements IBaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    /**
     * 获得当前事务中的session
     *
     * @return
     */
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 清楚会话连接
     */
    @Override
    public void clearSession() {
        Session session = this.getSession();
        session.flush();
        session.clear();
    }

    @Override
    public void delete(T entity) {
        Session session = this.getSession();
        session.delete(entity);
    }

    @Override
    public void delete(String id) {
        Session session = this.getSession();
        T t = this.loadById(id);
        session.delete(t);
    }

    /**
     * 根据HQL语句查询数据
     *
     * @param hql hql语句
     * @return 查询结果
     */
    <F> List<F> getByHQL(String hql) {
        Session session = this.getSession();
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * 根据SQL语句查询数据，结果不是实体，是Object列表
     *
     * @param sql sql语句
     * @return 查询结果
     */
    List<Objects> getBySQL(String sql) {
        Session session = this.getSession();
        SQLQuery query = session.createSQLQuery(sql);
        return query.list();
    }

    /**
     * 根据sql语句取得实体
     *
     * @param sql   完整的sql语句，需要有‘*’ 例如： select alias.* from table as alias
     * @param alias sql语句中表的别名
     * @param clazz 实体类型
     * @param <F>   实体类型
     * @return 实体列表
     */
    <F> List<F> getBySQL(String sql, String alias, Class<F> clazz) {
        Session session = this.getSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(alias, clazz);
        return query.list();
    }

    /**
     * 根据HQL语句查询数据的数量
     *
     * @param hql hql语句
     * @return 数量
     */
    long getCountByHQL(String hql) {
        Session session = this.getSession();
        Query query = session.createQuery(hql);
        return (Long) query.uniqueResult();
    }

    /**
     * 根据SQL语句查询数据的数量
     *
     * @param sql sql语句
     * @return 数量
     */
    long getCountBySql(String sql) {
        Session session = this.getSession();
        SQLQuery query = session.createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        return count.longValue();
    }

    @Override
    public String save(T entity) {
        Session session = this.getSession();
        return (String) session.save(entity);
    }

    @Override
    public void update(T entity) {
        Session session = this.getSession();
        session.update(entity);
    }


    long getCountByParameter(String table, String where, Parameter parameter) {

        return 0;
    }

}
