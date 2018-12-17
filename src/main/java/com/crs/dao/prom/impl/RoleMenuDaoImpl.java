package com.crs.dao.prom.impl;

import com.crs.dao.impl.BaseDaoImpl;
import com.crs.dao.prom.RoleMenuDAO;
import com.crs.entity.prom.RoleMenu;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.annotation.Resource;

@Resource
public class RoleMenuDaoImpl extends BaseDaoImpl implements RoleMenuDAO {
    @Override
    public void saveRole(RoleMenu rolemenu) {
        getSession().save(rolemenu);
    }

    @Override
    public boolean delete(String rolenum) {
        boolean flag = false;
        StringBuffer sql = new StringBuffer("DELETE  FROM base_role_menu  where role_num= "+rolenum+" ");
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        int rowCount = sqlQuery.executeUpdate();
        if (rowCount>0){
            flag = true;
        }
        return flag;
    }
    @Override
    public int deleteBymenuNum(String num) {
        String sql = "delete from  base_Role_Menu  where Menu_num='"+num+"'";
        return this.executeBySQL(sql);
    }
}
