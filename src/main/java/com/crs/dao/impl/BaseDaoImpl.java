package com.crs.dao.impl;

import com.crs.dao.BaseDao;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Resource
public class BaseDaoImpl implements BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> boolean add(T entity) {
        boolean bool = false;
        try
        {
            Serializable ser = this.getSession().save(entity);
            if (ser != null)
            {
                bool = true;
            }
        }
        catch (Exception e)
        {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;
    }

    @Override
    public <T> String addStringKey(T entity) {
        String id = null;
        try
        {
            id = (String) this.getSession().save(entity);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return id;
    }

    @Override
    public <T> Integer addNumKey(T entity) {
        Integer id = null;
        try
        {
            id = (Integer) this.getSession().save(entity);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return id;
    }


    @Override
    public <T> List<T> findByHql(String hql) {
        List<T> list = null;
        try
        {
            Query q=getSession().createQuery(hql);
            list = (List<T>) q.list();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public <T> List<T> findBySql(String sql) {
        try
        {
            List<T> list =  this.getSession().createSQLQuery(sql).list();
            return list;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> boolean editEntity(T entity) {
        boolean bool = false;
        try
        {
            Session session = sessionFactory.openSession();
            Transaction transaction =session.beginTransaction();
            session.update(entity);
            transaction.commit();
            session.close();
            bool = true;
        }
        catch (Exception e)
        {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;
    }

    @Override
    public boolean editHql(String hql) {
        boolean bool = false;
        try
        {
            Transaction tx =  getSession().beginTransaction();
            int i = getSession().createQuery(hql).executeUpdate();
            tx.commit();
            bool = i > 0 ? true : false;
        }
        catch (Exception e)
        {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;
    }

    @Override
    public int editNumHql(String hql) {
        int count = 0;
        try
        {
            Transaction tx =  getSession().beginTransaction();
            int i = getSession().createQuery(hql).executeUpdate();
            tx.commit();
            count = i;
        }
        catch (Exception e)
        {
            count = 0;
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public <T> boolean removeEntity(T entity) {
        boolean bool = false;
        try
        {
            Session session = sessionFactory.openSession();
            Transaction transaction =session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            session.close();
            bool = true;
        }
        catch (Exception e)
        {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;
    }

    @Override
    public <T> T getById(Class<T> clazz, int id) {
        T t = null;
        try
        {
            t = (T) this.getSession().get(clazz, id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return t;
    }

    @Override
    public <T> T getById(Class<T> clazz, String id) {
        T t = null;
        try
        {
            t = (T) this.getSession().get(clazz, id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return t;

    }

    @Override
    public <T> T getById(Class<T> clazz, Serializable id) {
        T t = null;
        try
        {
            t = (T) this.getSession().get(clazz, id);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return t;
    }

    @Override
    public <T> T getByHql(String hql) {
        T t = null;
        try
        {
            t = (T) this.getSession().createQuery(hql).setMaxResults(1).uniqueResult();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return t;
    }

    @Override
    public <T> List<T> getList(String hql) {
        List<T> list = null;
        try
        {
            Query q=getSession().createQuery(hql);
            list = (List<T>) q.list();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public boolean remove(String hql) {
        boolean bool = false;
        try
        {
            bool = this.executeByHQL(hql) > 0 ? true : false;
        }
        catch (Exception e)
        {
            bool = false;
            throw new RuntimeException(e);
        }
        return bool;
    }

    @Override
    public <T> List<T> getList(Class<T> clazz) {
        List<T> list = null;
        try
        {
            list = (List<T>) this.getSession().createCriteria(clazz);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return list;
    }


    /***
     * 执行Hsql查询
     *
     * @param hsql
     * @return
     */
    public Query createQuery(String hsql) {
        Query query = getSession().createQuery(hsql);
        return query;
    }

    public SQLQuery createSQLQuery(String sql) {
        return getSession().createSQLQuery(sql);
    }



    // 执行SQL查询返回值
    public List<?> getDataListBySQL(String sql) {
        Query query = createSQLQuery(sql);
        List<?> queryList = query.list();
        return queryList;
    }

    public int executeBySQL(String sql) {
        Query query = createSQLQuery(sql);
        return query.executeUpdate();
    }


    // 执行HQL查询返回值
    public List<?> getDataListByHQL(String hql) {
        Query query = createQuery(hql);
        List<?> queryList = query.list();
        return queryList;
    }

    public int executeByHQL(String hql) {
        Query query = createQuery(hql);
        return query.executeUpdate();
    }



}