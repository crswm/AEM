package com.crs.service.prom;

import com.crs.entity.prom.RoleMenu;

public interface RoleMenuService {
    void saveRole(RoleMenu rolemenu);
    boolean delete(String rolenum);
    int deleteBymenuNum(String MenuNum);

}
