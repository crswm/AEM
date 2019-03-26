package com.crs.dao.classify;

import com.crs.entity.classify.b_classify_base;

import java.util.List;

public interface classify_baseDao {
    void add(b_classify_base c);
    boolean update(b_classify_base c);
    boolean delete(b_classify_base c);
    List<b_classify_base> findClassifies(String str);
    b_classify_base findTbyid(int id);
    List <b_classify_base> findSons(int id);
}
