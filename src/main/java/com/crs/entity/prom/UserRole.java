package com.crs.entity.prom;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "base_User_Role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -8904038605517928580L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_Num")
    private String userNum;


    @Column(name = "role_num")
    private String roleNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }
}
