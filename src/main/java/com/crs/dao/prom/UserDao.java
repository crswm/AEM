package com.crs.dao.prom;

import com.crs.entity.prom.User;

import java.util.List;


public interface UserDao {

     void saveUser(User user);

     User queryUser(User user);

     User findUserById(Integer id);

     User findUserPassword(String userName,String email);

     List<User> findUser(List<Object> params,String hql);

     void updateUser(User user);

     User findUserByUserName(String userName);

    List<User> findUserRoles();

    List<String> findRoles(String userName);

}