package com.crs.dao.prom;

import com.crs.entity.prom.RoleMenu;

public interface RoleMenuDAO {
    void saveRole(RoleMenu rolemenu);
    boolean delete(String rolenum);
    int deleteBymenuNum(String num);

}
