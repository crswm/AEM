package com.crs.dao.prom.impl;

import com.crs.dao.impl.BaseDaoImpl;
import com.crs.dao.prom.MenuDao;
import com.crs.entity.prom.Menu;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.List;

@Resource
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {

    @Override
    public List<Menu> findMenu(List<Object> params, String hql) {
        Query query = getSession().createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                query.setParameter(i, params.get(i));
            }
        }
        return query.list();
    }

    @Override
    public void save(Menu menu) {
//        getSession().save(menu);
        this.add(menu);
    }

    @Override
    public Menu findMenuByName(String name) {
        String hql="from Menu where name=?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, name);
        return (Menu) query.uniqueResult();
    }

    @Override
    public void updateMenu(Menu m) {
        //getSession().update(m);
        this.editEntity(m);
    }

    @Override
    public int findLevelByNum(int num) {
        String hql="from Menu where num=?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, num);
        Menu m = (Menu) query.uniqueResult();
        return m.getLevel();
    }

    @Override
    public Menu findMenuByNum(int num) {
        String hql="from Menu where num=? order by id";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, num);
        Menu m = (Menu) query.uniqueResult();
        return m;
    }

    @Override
    public void updateNum(Menu menu) {
        String hql="update Menu set num='"+menu.getNum()+"' where id="+menu.getId();
        int i = getSession().createQuery(hql).executeUpdate();
        if (i>0){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }

    }

    @Override
    public Menu findMenuById(int id) {
        Menu menu = this.getById(Menu.class,id);
        return menu;
    }

    @Override
    public boolean deleteById(Menu menu ) {
        return this.removeEntity(menu);
    }


}
