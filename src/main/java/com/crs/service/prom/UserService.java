package com.crs.service.prom;

import com.crs.entity.prom.Role;
import com.crs.entity.prom.User;

import java.util.List;
import java.util.Map;


public interface UserService {
     void addUser(User user);

     User queryUser(User user);

     User getUserById(Integer id);

     User findUserPassword(String userName,String email);

     List<User> findUsers(Map<String, Object> map);

     void updateUser(User user);

     void saveUser(User user);

     User findUserByUserName(String userName);

     List<User> findUserRoles();

     List<Role> findRoles(String userNum);


}