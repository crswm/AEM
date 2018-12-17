package com.crs.entity.prom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "base_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -3320699586841202224L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role_num")
    private String roleNum;

    @Column(name = "role_name")
    private String roleName;


    @Column(name = "create_Date")
    private Date createDate;

    @Column(name = "role_descrition")
    private String description;

    @Transient
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
