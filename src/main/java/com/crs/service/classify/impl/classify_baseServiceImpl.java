package com.crs.service.classify.impl;

import com.crs.dao.classify.classify_baseDao;
import com.crs.entity.classify.b_classify_base;
import com.crs.service.classify.classify_baseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class classify_baseServiceImpl implements classify_baseService {
    @Autowired
    private classify_baseDao classify_baseDao;

    @Override
    public void add(b_classify_base c) {
        classify_baseDao.add(c);
    }

    @Override
    public boolean update(b_classify_base c) {
        return classify_baseDao.update(c);
    }

    @Override
    public boolean delete(b_classify_base c) {
        return classify_baseDao.delete(c);
    }

    @Override
    public List<b_classify_base> findClassifies(String str) {
        return classify_baseDao.findClassifies(str);
    }

    @Override
    public b_classify_base findTbyid(int id) {
        return classify_baseDao.findTbyid(id);
    }

    @Override
    public List<b_classify_base> findSons(int id) {
        return classify_baseDao.findSons(id);
    }
}
