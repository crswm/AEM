package com.crs.service.prom.impl;

import com.crs.dao.prom.RoleDAO;
import com.crs.dao.prom.UserDao;
import com.crs.entity.prom.Role;
import com.crs.entity.prom.User;
import com.crs.service.prom.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired //我习惯用这种方式，比较方便
    private UserDao userDao;

    @Autowired
    private RoleDAO roleDAO;


    public void addUser(User user) {
        userDao.saveUser(user);
    }

    public User queryUser(User user) {
        return userDao.queryUser(user);
    }

    public User getUserById(Integer id) {
        return userDao.findUserById(id);
    }

    public User findUserPassword(String userName, String email) {
        return userDao.findUserPassword(userName, email);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public List<User> findUsers(Map<String, Object> map) {
        StringBuffer hql=new StringBuffer("from User where 1=1 ");
        List<Object> params=new LinkedList<Object>();

        if (map.get("userName")!=null) {
            hql.append(" and userName=? ");
            params.add(map.get("userName"));
        }
        if (map.get("email")!=null) {
            hql.append(" and email=? ");
            params.add(map.get("email"));
        }
        if (map.get("sex")!=null) {
            hql.append(" and sex=? ");
            params.add(map.get("sex"));
        }
        if (map.get("valid")!=null){
            hql.append(" and valid=? ");
            params.add(map.get("valid"));
        }
        System.out.println(hql.toString());
        return userDao.findUser(params, hql.toString());
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    @Override
    public List<User> findUserRoles() {
        return userDao.findUserRoles();
    }

    @Override
    public List<Role> findRoles(String userNum) {
        List<Role> roles = new ArrayList<Role>();
        List<String> roleNums = userDao.findRoles(userNum);
        for (String s : roleNums){
            Role role = roleDAO.findRoleByNum(s);
            if (role!=null){
                roles.add(role);
            }
        }
        return roles;
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }
}