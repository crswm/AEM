package com.crs.service.goods;

import com.crs.entity.goods.goods;

import java.util.List;

public interface goodsService {
    void add(goods c);
    boolean update(goods c);
    boolean delete(goods c);
    List<goods> findTs(String str);
    goods findTbyid(int id);
}
