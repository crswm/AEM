package com.crs.entity.goods;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SPU_base")
public class SPU_base implements Serializable {
    private static final long serialVersionUID = 6961592191078633616L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "seller_id")
    private int  seller_id;

    @Column(name = "brand_id")
    private int brand_id;

    @Column(name = "stock_id")
    private int stock_id;

    @Column(name = "groud_id")
    private int groud_id;

    @Column(name = "cat_id")
    private int cat_id;

    @Column(name = "goods_name")
    private String goods_name;

    @Column(name = "creator")
    private String creator;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "updator")
    private String  updator;

    @Column(name = "update_date")
    private Date  update_date;

    @Column(name = "deletor")
    private String  deletor;

    @Column(name = "del_date")
    private Date  del_date;

    @Column(name = "del")
    private Boolean  del;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getGroud_id() {
        return groud_id;
    }

    public void setGroud_id(int groud_id) {
        this.groud_id = groud_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getDeletor() {
        return deletor;
    }

    public void setDeletor(String deletor) {
        this.deletor = deletor;
    }

    public Date getDel_date() {
        return del_date;
    }

    public void setDel_date(Date del_date) {
        this.del_date = del_date;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}
