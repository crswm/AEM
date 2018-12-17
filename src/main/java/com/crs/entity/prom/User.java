package com.crs.entity.prom;


import com.crs.entity.seller.Seller;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "base_user")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class User implements Serializable {
    private static final long serialVersionUID = -529626353441075936L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_Num")
    private String userNum;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    //1-男  0-女
    @Column(name = "sex")
    private Integer sex;

    @Column(name = "create_Date")
    private Date createDate;

    @Column(name = "valid")
    private String valid;


    @Column(name = "is_seller")
    private String is_seller;

//    @JsonManagedReference
//    @OneToMany( fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
////    @JsonIgnore
//    private Set<Seller> sellers;



    @Transient
    private String roleName;

    @Transient
    private List<Menu> prom;

    @Transient
    private List<Seller> sellers;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public List<Menu> getProm() {
        return prom;
    }

    public void setProm(List<Menu> prom) {
        this.prom = prom;
    }

    public String getIs_seller() {
        return is_seller;
    }

    public void setIs_seller(String is_seller) {
        this.is_seller = is_seller;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    @Override
    public String toString() {
        return "User {id=" + id + ", userName=" + userName + ", password="
                + password + ", email=" + email + ", sex=" + sex + " , pro=" + prom.toString() + " }";
    }

}
