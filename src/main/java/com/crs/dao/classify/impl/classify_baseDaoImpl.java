package com.crs.dao.classify.impl;

import com.crs.dao.classify.classify_baseDao;
import com.crs.dao.impl.BaseDaoImpl;
import com.crs.entity.classify.b_classify_base;

import java.util.ArrayList;
import java.util.List;

public class classify_baseDaoImpl extends BaseDaoImpl implements classify_baseDao {
    @Override
    public void add(b_classify_base c) {
        super.add(c);
    }

    @Override
    public boolean update(b_classify_base c) {
        return super.editEntity(c);
    }

    @Override
    public boolean delete(b_classify_base c) {
        return super.removeEntity(c);
    }

    @Override
    public List<b_classify_base> findClassifies(String str) {
        String hql = " from b_classify_base where 1=1 "+str;
        return super.findByHql(hql);
    }

    @Override
    public b_classify_base findTbyid(int id) {
        return super.getById(b_classify_base.class,id);
    }

    @Override
    public List<b_classify_base> findSons(int id) {
        List<b_classify_base> result = new ArrayList<b_classify_base>();
        String hql = "from b_classify_base where 1=1 and pid="+id;
        result = super.findByHql(hql);
        return result;
    }

}
