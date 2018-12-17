package com.crs.service.prom.impl;

import com.crs.dao.prom.MenuDao;
import com.crs.entity.prom.Menu;
import com.crs.service.prom.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findMenu(Map<String, Object> map) {
        StringBuffer hql=new StringBuffer("from Menu where 1=1 ");
        List<Object> params=new LinkedList<Object>();
        if (map.get("MenuNum")!=null) {
            hql.append(" and roleNum=? ");
            params.add(map.get("roleNum"));
        }
        List<Menu> menus = this.menuDao.findMenu(params,hql.toString());
        return menus;
    }

    @Override
    public void save(Menu menu) {
        menuDao.save(menu);
    }

    @Override
    public Menu findMenuByName(String name) {
        return menuDao.findMenuByName(name);
    }

    @Override
    public void updateMenu(Menu m) {
        menuDao.updateMenu(m);
    }

    @Override
    public int findLevelByNum(int num) {
        return menuDao.findLevelByNum(num);
    }

    @Override
    public void updateNum(Menu menu) {
        menuDao.updateNum(menu);
    }

    @Override
    public Menu findMenuById(int id) {
        return menuDao.findMenuById(id);
    }

    @Override
    public boolean deleteById(Menu menu) {
        return menuDao.deleteById(menu);
    }
}
