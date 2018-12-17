package com.crs.service.prom.impl;

import com.crs.dao.prom.RoleMenuDAO;
import com.crs.entity.prom.RoleMenu;
import com.crs.service.prom.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuDAO roleMenuDAO;

    @Override
    public void saveRole(RoleMenu rolemenu) {
        roleMenuDAO.saveRole(rolemenu);
    }

    @Override
    public boolean delete(String rolenum) {
       return roleMenuDAO.delete(rolenum);
    }

    @Override
    public int deleteBymenuNum(String MenuNum) {
        return roleMenuDAO.deleteBymenuNum(MenuNum);
    }
}
