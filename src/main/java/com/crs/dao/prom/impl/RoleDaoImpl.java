package com.crs.dao.prom.impl;

import com.crs.dao.impl.BaseDaoImpl;
import com.crs.dao.prom.RoleDAO;
import com.crs.entity.prom.Role;
import org.hibernate.*;
import org.hibernate.transform.Transformers;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Resource
public class RoleDaoImpl extends BaseDaoImpl implements RoleDAO {
    @Override
    public void saveRole(Role role) {
        getSession().save(role);
    }


    @Override
    public Role findRoleById(Integer id) {
        String hql = "from Role where id=?";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, id);
        Role role = (Role) query.uniqueResult();
        return role;
    }

    @Override
    public List<Role> findRole(List<Object> params, String hql) {
        Query query = getSession().createQuery(hql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                query.setParameter(i, params.get(i));
            }
        }
        return query.list();
    }

    @Override
    public void updateRole(Role role) {
        super.editEntity(role);
    }

    @Override
    public Role findRoleByNum(String RoleNum) {
        String hql = "from Role where roleNum=? order by id";
        Query query = getSession().createQuery(hql);
        query.setParameter(0, RoleNum);
        Role role = (Role) query.uniqueResult();
        return role;
    }

    @Override
    public List<Role> findUserRoleRalation() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String sql = "select t.id,t.role_num,t.role_name,t.create_Date,t.role_descrition,count(b.role_num) as count from base_role t " +
                "left join base_User_Role b on t.role_num = b.role_num group by t.role_num,t.role_name,b.role_num,t.id,t.create_Date,t.role_descrition ";
        Query query = getSession().createSQLQuery(sql);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> result = query.list();
        List<Role> roles = new ArrayList<Role>();
        for (int i = 0; i < result.size(); i++) {
            Map<String, Object> resultx = result.get(i);
            Role role = new Role();
            role.setId(Integer.parseInt(resultx.get("id").toString()));
            role.setRoleName(resultx.get("role_name").toString());
            role.setRoleNum(resultx.get("role_num").toString());
            role.setCreateDate(sdf.parse(resultx.get("create_Date").toString()));
            role.setDescription(null != resultx.get("role_descrition") ? resultx.get("role_descrition").toString() : null);
            role.setCount(null != resultx.get("count").toString() ? Integer.parseInt(resultx.get("count").toString()) : null);
            roles.add(role);
        }
//        List<Role> roles= getSession().createSQLQuery(sql).list();
        //System.out.println(roles.get(0).toString());
        return roles;
    }

    @Override
    public List checkUserRole(List<Object> params, String sql) {
        Query query = getSession().createSQLQuery(sql);
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                query.setParameter(i, params.get(i));
            }
        }
        return query.list();
    }

    @Override
    public boolean saveUserRole(String userNum, String roleNum) {
        boolean flag = false;
        StringBuffer sql = new StringBuffer("insert into base_user_role (user_Num,role_num)value(" + userNum + "," + roleNum + ") ");
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        int rowCount = sqlQuery.executeUpdate();
        if (rowCount>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public  List<Map<String, Object>> getMenuListByroleNum(String roleNum) {
        StringBuffer sql = new StringBuffer("select Menu_num  from base_role_menu t where  t.role_num='"+roleNum+"' order by id");
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> list = sqlQuery.list();
        return list;
    }

}
