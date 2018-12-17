package com.crs.dao.prom;

import com.crs.entity.prom.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> findMenu(List<Object> params, String hql);
    void save(Menu menu);
    Menu findMenuByName(String name);
    void updateMenu(Menu m);
    int findLevelByNum(int num);
    Menu findMenuByNum(int num);
    void updateNum(Menu menu);
    Menu findMenuById(int id);
    boolean deleteById(Menu menu);
}
