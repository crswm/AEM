package com.crs.service.prom;

import com.crs.entity.prom.Menu;
import com.crs.entity.prom.Role;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface RoleService {
    void saveRole(Role role);

    Role findRoleById(Integer id);

    List<Role> findRole(Map<String, Object> map);

    void updateRole(Role role);

    Role findRoleByNum(String RoleNum);

    List<Role> findUserRoleRalation() throws ParseException;

    boolean checkUserRole(String userNum, String roleNum);

    boolean saveUserRole(String userNum, String roleNum);

    List<Menu> getMenuListByroleNum(String roleNum);

}


