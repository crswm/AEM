package com.crs.service.prom;

import com.crs.entity.prom.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<Menu> findMenu(Map<String, Object> map);
    void save(Menu menu);
    Menu findMenuByName(String name);
    void updateMenu(Menu m);
    int findLevelByNum(int num);
    void updateNum(Menu menu);
    Menu findMenuById(int id);
    boolean deleteById(Menu menu);
}
