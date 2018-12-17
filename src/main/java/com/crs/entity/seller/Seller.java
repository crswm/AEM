package com.crs.entity.seller;

import com.crs.entity.prom.User;
import com.fasterxml.jackson.annotation.*;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seller")
public class Seller implements Serializable {
    private static final long serialVersionUID = -9004927210642855725L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "user_id")
    private String  userId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;//服务者类型，销售，维修，发布信息等。。。

    @Column(name = "is_del")
    private Boolean is_del;//是否移除

    @Column(name = "is_work")
    private Boolean is_work;//是否营业

    @Column(name = "mobile")
    private String mobile;


    @Transient
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIs_del() {
        return is_del;
    }

    public void setIs_del(Boolean is_del) {
        this.is_del = is_del;
    }

    public Boolean getIs_work() {
        return is_work;
    }

    public void setIs_work(Boolean is_work) {
        this.is_work = is_work;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
