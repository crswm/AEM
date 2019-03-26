package com.crs.dao.goods.impl;

import com.crs.dao.goods.goodsDao;
import com.crs.dao.impl.BaseDaoImpl;
import com.crs.entity.goods.goods;

import java.util.List;

public class goodsDaoImpl extends BaseDaoImpl implements goodsDao {
    @Override
    public void add(goods c) {
        super.add(c);
    }

    @Override
    public boolean update(goods c) {
        return super.editEntity(c);
    }

    @Override
    public boolean delete(goods c) {
        return super.removeEntity(c);
    }

    @Override
    public List<goods> findTs(String str) {
        String hql = "from goods where 1=1 "+str;
        return super.findByHql(hql);
    }

    @Override
    public goods findTbyid(int id) {
        return super.getById(goods.class,id);
    }
}
