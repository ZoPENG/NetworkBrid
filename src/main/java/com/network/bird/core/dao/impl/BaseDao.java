package com.network.bird.core.dao.impl;

import com.network.bird.common.exception.BaseException;
import com.network.bird.common.exception.NotImplementedException;
import com.network.bird.common.utils.Maps;
import com.network.bird.core.dao.IBaseDao;
import com.network.bird.core.parameter.Parameter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String sql = buildSqlForCount(table, where, parameter);
        return 0;
    }

    /**
     * 构建查询数量的sql语句
     * @return 完整的查询sql，例如：select count(*) from table as table_a where table_a.id=1
     */
    private String buildSqlForCount(String table, String where, Parameter parameter) {

        Map<String, String> mapping = getMapping(parameter);
        String querySql = getQuerySql();
        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) ");
        sql.append(querySql.substring(querySql.indexOf(".") + 2));
        if(StringUtils.hasText(table)){
            sql.append(" ");
            sql.append(table);
        }
        sql.append(" where 1=1");
        if(StringUtils.hasText(where)) {
            sql.append(" and ").append(where);
        }
        if(parameter != null) {
            String whereStr = parameter.getQuery(mapping);
            if (StringUtils.hasText(whereStr)) {
                sql.append(" and ").append(whereStr);
            }
        }
        return sql.toString();
    }
    /**
     * 构建查询数据列表的sql语句
     * @return 完整的查询sql，例如：select count(*) from table as table_a where table_a.id=1
     */
    private String buildSqlForList(String table, String where, Parameter parameter) {
        Map<String, String> mapping = getMapping(parameter);
        StringBuilder sql = new StringBuilder();
        //拼接表
        sql.append(getQuerySql());
        if(StringUtils.hasText(table)) {
            sql.append(" ");
            sql.append(table);
        }
        sql.append(" where 1=1");
        //拼接查询条件
        if(StringUtils.hasText(where)) {
            sql.append(" and ").append(where);
        }
        if(parameter != null) {
            String whereStr = parameter.getQuery(mapping);
            if (StringUtils.hasText(whereStr)) {
                sql.append(" and ").append(whereStr);
            }
            List<String> sortFields = parameter.getSortField();
            List<Boolean> isAscs = parameter.getIsAsc();
            StringBuilder orderStr = new StringBuilder();
            for(int i = 0; i < sortFields.size(); i++){
                orderStr.append(mapping.get(sortFields.get(i))).append(isAscs.get(i) ? "asc," : "desc,");
            }
            if(orderStr.length() > 0) {
                sql.append(" order by ").append(orderStr.deleteCharAt(orderStr.length() - 1));
            }
            //拼接分页条件 TODO
            sql.append(" limit ").append(parameter.getFirstResult()).append(",").append(parameter.getMaxResults());
        }
        return sql.toString();
    }

    /**
     * 获得搜索类中属性名和数据表中列名的映射关系。
     * <p>默认搜索类中属性名和数据表中列名相同</p>
     * @param parameter 搜索参数,可以为null
     * @return 搜索类中属性名和数据表中列名的映射关系，key:搜索类的属性名，value:数据表中列的名称，不是实体类的属性名
     */
    private Map<String,String> getMapping(Parameter parameter) {

        Map<String, String> mapping = Maps.newHashMap();
        if(!mapping.isEmpty()){// TODO
            return mapping;
        }
        if(null == parameter){
            return mapping;
        }
        String alias = getQuerySqlAlias();
        Set<String> names = parameter.getPropertyName();
        for(String name : names){
            mapping.put(name, alias + name);
        }
        return mapping;
    }
    /**
     * 从查询sql语句中截取出表的别名
     * @return 表的别名
     */
    private String getQuerySqlAlias() {
        String sql =  getQuerySql();
        Matcher matcher = Pattern.compile("^select\\s+[0-9A-Za-z_-]+[.][*]]", Pattern.CASE_INSENSITIVE).matcher(sql);
        if(matcher.find()){
            return matcher.group().substring(6, matcher.group().length() - 2).trim();
        }else{
            throw new BaseException("sql应该以“select 别名.*”开头，" + sql);
        }
    }

    /**
     * 获得查询数据列表的sql语句。
     * <p>sql应该以“select 别名.*”开头，例如：select table_0.* from table as table_0</p>
     * @return 查询数据列表的sql语句。
     */
    private String getQuerySql() {
        //TODO
        throw new NotImplementedException();
    }

}
