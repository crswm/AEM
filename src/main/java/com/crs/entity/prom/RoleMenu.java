package com.crs.entity.prom;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "base_Role_Menu")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 3030865214715889202L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role_num")
    private String roleNum;

    @Column(name = "Menu_num")
    private String menuNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }

    public String getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }
}
