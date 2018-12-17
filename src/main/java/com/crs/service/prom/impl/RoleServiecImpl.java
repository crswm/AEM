package com.crs.service.prom.impl;

import com.crs.dao.prom.MenuDao;
import com.crs.dao.prom.RoleDAO;
import com.crs.entity.prom.Menu;
import com.crs.entity.prom.Role;
import com.crs.service.prom.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiecImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private MenuDao menuDao;

    @Override
    public void saveRole(Role role) {
        this.roleDAO.saveRole(role);
    }

    @Override
    public Role findRoleById(Integer id) {
        Role role = this.roleDAO.findRoleById(id);
        return role;
    }

    @Override
    public List<Role> findRole(Map<String, Object> map) {
        StringBuffer hql=new StringBuffer("from Role where 1=1 ");
        List<Object> params=new LinkedList<Object>();
        if (map.get("roleNum")!=null) {
            hql.append(" and roleNum=? ");
            params.add(map.get("roleNum"));
        }
        if (map.get("roleName")!=null) {
            hql.append(" and roleName=? ");
            params.add(map.get("roleName"));
        }
        if (map.get("roleName")!=null) {
            hql.append(" and roleName=? ");
            params.add(map.get("roleName"));
        }
        List<Role> roles = this.roleDAO.findRole(params,hql.toString());
        return roles;
    }

    @Override
    public void updateRole(Role role) {
        this.roleDAO.updateRole(role);
    }

    @Override
    public Role findRoleByNum(String RoleNum) {
        Role role = this.roleDAO.findRoleByNum(RoleNum);
        return role;
    }

    @Override
    public List<Role> findUserRoleRalation() throws ParseException {
        return roleDAO.findUserRoleRalation();
    }

    @Override
    public boolean checkUserRole(String usernum, String RoleNum) {
        StringBuffer sql = new StringBuffer("SELECT b.* from base_user_role b where 1 =1");//   b.user_Num = 1001 and b.role_num=10004
        List<Object> params=new LinkedList<Object>();
        if (usernum!=null) {
            sql.append(" and user_Num=? ");
            params.add(usernum);
        }
        if (RoleNum!=null) {
            sql.append(" and role_num=? ");
            params.add(RoleNum);
        }
        sql.append("order by id asc");
        List list = this.roleDAO.checkUserRole(params,sql.toString());
        if (list.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean saveUserRole(String usernum, String RoleNum) {
       return roleDAO.saveUserRole(usernum,RoleNum);
    }

    @Override
    public List<Menu> getMenuListByroleNum(String roleNum) {
        List<Menu> result = new ArrayList<Menu>();
        List<Map<String, Object>> list= roleDAO.getMenuListByroleNum(roleNum);
        for (int i = 0;i<list.size();i++){
            Menu m = menuDao.findMenuByNum(Integer.parseInt(list.get(i).get("Menu_num").toString()));
            if (m!=null){
                result.add(m);
            }
        }
        return result;
    }

}
