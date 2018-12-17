package com.crs.dao.prom;

import com.crs.entity.prom.Role;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RoleDAO {
     void saveRole(Role role);

     Role findRoleById(Integer id);

     List<Role> findRole(List<Object> params, String hql);

     void updateRole(Role role);

     Role findRoleByNum(String RoleNum);

     List<Role> findUserRoleRalation() throws ParseException;

     List checkUserRole(List<Object> params,String sql);

     boolean saveUserRole(String usernum ,String RoleNum);

     List<Map<String, Object>> getMenuListByroleNum(String roleNum);

}
