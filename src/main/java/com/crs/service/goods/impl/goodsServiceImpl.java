package com.crs.service.goods.impl;

import com.crs.dao.goods.goodsDao;
import com.crs.entity.goods.goods;
import com.crs.service.goods.goodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class goodsServiceImpl implements goodsService {

    @Autowired
    private goodsDao goodsDao;

    @Override
    public void add(goods c) {
        goodsDao.add(c);
    }

    @Override
    public boolean update(goods c) {
        return goodsDao.update(c);
    }

    @Override
    public boolean delete(goods c) {
        return goodsDao.delete(c);
    }

    @Override
    public List<goods> findTs(String str) {
        return goodsDao.findTs(str);
    }

    @Override
    public goods findTbyid(int id) {
        return goodsDao.findTbyid(id);
    }
}
