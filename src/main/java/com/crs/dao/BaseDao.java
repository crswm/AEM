package com.crs.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {
    /**
     * <b>function:</b>增加一个entity对象，返回是否添加成功
     *
     * @param T      对象类型
     * @param entity 实体类
     * @return boolean true/false
     * @
     * @createDate 2016-11-25 下午02:44:34
     * @author ending
     */
    public <T> boolean add(T entity)  ;

    /**
     * <b>function:</b>增加一个entity对象，返回String主键
     *
     * @param T      对象类型
     * @param entity 实体类
     * @return String
     * @
     * @createDate 2016-11-25 下午02:45:15
     * @author ending
     */
    public <T> String addStringKey(T entity)  ;

    /**
     * <b>function:</b>增加一个entity对象，返回Integer主键
     *
     * @param T      对象类型
     * @param entity 实体类
     * @return Integer
     * @
     * @createDate 2016-11-25 下午02:45:15
     * @author ending
     */
    public <T> Integer addNumKey(T entity)  ;



    /**
     * <b>function:</b>传入一个hql语句，返回list集合
     *
     * @param hql
     * @return List
     * @
     * @createDate 2016-11-25 下午02:53:14
     * @author ending
     */
    public <T> List<T> findByHql(String hql)  ;



    /**
     * <b>function:</b>执行sql查询语句，获取list集合
     *
     * @param sql
     * @return List
     * @
     * @createDate 2016-11-25 下午02:56:03
     * @author ending
     */
    public <T> List<T> findBySql(String sql)  ;

    /**
     * <b>function:</b>修改实体的数据，返回boolean结果
     *
     * @param entity
     * @return boolean
     * @
     * @createDate 2016-11-25 下午03:01:08
     * @author ending
     */
    public <T> boolean editEntity(T entity)  ;

    /**
     * <b>function:</b>执行hql语句，返回boolean结果
     *
     * @param hql
     * @return boolean
     * @
     * @createDate 2016-11-25 下午03:01:08
     * @author ending
     */
    public boolean editHql(String hql)  ;

    /**
     * <b>function:</b>执行hql语句，返回执行结果影响的行数
     *
     * @param hql
     * @return Integer
     * @
     * @createDate 2016-11-25 下午03:04:10
     * @author ending
     */
    public int editNumHql(String hql)  ;

    /**
     * <b>function:</b>传入要删除的实体，返回boolean结果
     *
     * @param entity
     * @return boolean
     * @
     * @createDate 2016-11-25 下午03:08:33
     * @author ending
     */
    public <T> boolean removeEntity(T entity)  ;

    /**
     * <b>function:</b>传入要实体类的class和int主键，返回具体实体
     *
     * @param clazz
     * @param id
     * @return T
     * @
     * @createDate 2016-11-25 下午03:12:07
     * @author ending
     */
    public <T> T getById(Class<T> clazz, int id)  ;

    /**
     * <b>function:</b>传入要实体类的class和String主键，返回具体实体
     *
     * @param clazz
     * @param id
     * @return T
     * @
     * @createDate 2016-11-25 下午03:12:07
     * @author ending
     */
    public <T> T getById(Class<T> clazz, String id)  ;

    /**
     * <b>function:</b>传入要实体类的class和Serializable主键，返回具体实体
     *
     * @param clazz
     * @param id
     * @return T
     * @
     * @createDate 2016-11-25 下午03:12:07
     * @author ending
     */
    public <T> T getById(Class<T> clazz, Serializable id)  ;

    /**
     * <b>function:</b>传入hql语句，返回实体
     *
     * @param hql
     * @return T
     * @
     * @createDate 2016-11-25 下午03:16:21
     * @author ending
     */
    public <T> T getByHql(String hql)  ;

    /**
     * <b>function:</b>传入hql语句，返回实体集合
     *
     * @param hql
     * @return List
     * @
     * @createDate 2016-11-25 下午03:17:26
     * @author ending
     */
    public <T> List<T> getList(String hql)  ;

    /**
     * <b>function:</b>传入hql语句删除数据，返回执行结果
     *
     * @param hql
     * @return boolean
     * @
     * @createDate 2016-11-25 下午03:18:44
     * @author ending
     */
    public boolean remove(String hql)  ;

    /**
     * <b>function:</b>动态查询
     *
     * @param clazz
     * @return List
     * @
     * @createDate 2016-11-25 下午03:18:44
     * @author ending
     */
    public <T> List<T> getList(Class<T> clazz)  ;

    /**
     * <b>function:</b>传入查询语句和查询总条数的hql，当前页数，一页显示多少数据，用list集合返回
     * @createDate 2016-11-25 下午03:26:39
     * @author ending
     * @param queryHql
     * @param queryCountHql
     * @param firstResult
     * @param maxResult
     * @return List
     * @
     * */
//    public List<?> showPage(String queryHql,String queryCountHql,int firstResult,int maxResult)  ;
    /**
     * <b>function:</b>传入查询语句和查询总条数的hql，page分页对象，用list集合返回
     * @createDate 2016-11-25 下午03:26:39
     * @author ending
     * @param queryHql
     * @param queryCountHql
     * @param page
     * @return List
     * @
     * */
//    public <T> List<T> showPage(String queryHql,String queryCountHql,Page<T> page)  ;
    /**
     * <b>function:</b>传入查询语句和查询总条数的hql，DetachedCriteria动态查询条件进行分页，用list集合返回
     * @createDate 2016-11-25 下午03:26:39
     * @author ending
     * @param queryHql
     * @param criteria
     * @param firstResult
     * @param maxResult
     * @return List
     * @
     * */
//    @SuppressWarnings("rawtypes")
//    public List showPage(String queryHql, DetachedCriteria criteria, int firstResult, int maxResult)  ;
    /**
     * <b>function:</b>传入查询语句和查询总条数的hql，DetachedCriteria动态查询条件进行分页，分页对象page，用list集合返回
     * @createDate 2016-11-25 下午03:26:39
     * @author ending
     * @param queryHql
     * @param criteria
     * @param page
     * @return List
     * @
     * */
//    public <T> List<T> showPage(String queryHql,DetachedCriteria criteria,Page<T> page)  ;


    /**
     * <b>function:</b>提供session使用
     *
     * @return Session
     * @createDate 2016-11-25 下午03:39:22
     * @author ending
     */
    public Session getSession();
}
