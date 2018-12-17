package com.crs.dao.prom.impl;

import com.crs.dao.impl.BaseDaoImpl;
import com.crs.dao.prom.UserDao;
import com.crs.entity.prom.User;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Resource
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private LocalSessionFactoryBean sessionFactory;

    @Override
    public void saveUser(User user) {
        getSession().save(user);
}
    @SuppressWarnings("unchecked")
    @Override
    public User queryUser(User user) {
        String hql="from User where userName=? and password=?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, user.getUserName());
        query.setParameter(1, user.getPassword());

        User currentUser=null;

        List<User> userList=query.list();
        if (userList!=null && userList.size()>0) {
            currentUser=userList.get(0);
        }

        return currentUser;
    }

    @Override
    public User findUserById(Integer id) {
        String hql="from User where id=?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, id);
        User user=(User) query.uniqueResult();
        return user;
    }

    @Override
    public User findUserPassword(String userName, String email) {
        String hql="from User where userName=? and email=?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, userName);
        query.setParameter(1, email);
        User currentUser=(User) query.uniqueResult();

        return currentUser;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUser(List<Object> params, String hql) {
        Query query=getSession().createQuery(hql);
        if (params!=null && params.size()>0) {
            for(int i=0;i<params.size();i++){
                query.setParameter(i, params.get(i));
            }
        }
        return query.list();
    }

    @Override
    public void updateUser(User user) {
        super.editEntity(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        String hql="from User where userName=?";
        Query query=getSession().createQuery(hql);
        query.setParameter(0, userName);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> findUserRoles() {
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String sql = "SELECT " +
                " u.id," +
                " u.user_Num, " +
                " u.email, " +
                " u.create_Date, " +
                " u.tel, " +
                " u.user_name, " +
                " u.valid, " +
                " u.sex, " +
                " group_concat( r.role_name separator  ',' ) AS role_name  " +
                " FROM " +
                " base_user u " +
                " LEFT JOIN base_user_role b ON u.user_Num = b.user_Num " +
                " LEFT JOIN base_role r ON r.role_num = b.role_num  " +
                "GROUP BY " +
                " u.id, " +
                " u.user_Num, " +
                " u.email, " +
                " u.create_Date, " +
                " u.tel, " +
                " u.user_name, " +
                " u.valid, " +
                " u.sex";
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> result = query.list();
        List<User> roles= new ArrayList<User>();
        for (int i =0;i<result.size();i++){
            Map<String,Object> resultx = result.get(i);
            User user = new User();
            user.setId(Integer.parseInt(resultx.get("id").toString()));
            user.setUserNum(resultx.get("user_Num").toString());
            user.setEmail(null !=resultx.get("email") ? resultx.get("email").toString():null);
            try {
                user.setCreateDate( sdf.parse(resultx.get("create_Date").toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            user.setTel(null !=resultx.get("tel") ? resultx.get("tel").toString():null);
            user.setUserName(resultx.get("user_name").toString());
            user.setValid(resultx.get("valid").toString());
            user.setSex(null !=resultx.get("sex") ? Integer.parseInt(resultx.get("sex").toString()):null);
            user.setRoleName(null !=resultx.get("role_name") ? resultx.get("role_name").toString() :null);
            roles.add(user);

        }
        return roles;
    }

    @Override
    public List<String> findRoles(String userNum) {
        String sql = "select role_num from base_user_role where user_Num=?";
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setParameter(0,userNum);
        List<Map<String,Object>> result = query.list();
        List<String> roleNums = new  ArrayList();
        for (int i =0;i<result.size();i++){
            roleNums.add(null !=result.get(i).get("role_num")?result.get(i).get("role_num").toString():null);
        }
        return roleNums;
    }

}