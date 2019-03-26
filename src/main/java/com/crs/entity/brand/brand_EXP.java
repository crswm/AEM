package com.crs.entity.brand;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "brand_EXP")
public class brand_EXP implements Serializable {
    private static final long serialVersionUID = -8166219918965438533L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "brand_id")
    private int brand_id;

    @Column(name = "brand_chinese_text")
    private String brand_chinese_text;

    @Column(name = "brand_english_text")
    private String brand_english_text;

    @Column(name = "brand_logo")
    private String brand_logo;

    @Column(name = "enter_time")
    private Date enter_time;

    @Column(name = "brand_origin")
    private String brand_origin;

    @Column(name = "import_type")
    private String import_type;

    @Column(name = "brand_type")
    private String brand_type;

    @Column(name = "brand_yieldly")
    private String brand_yieldly;

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

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_chinese_text() {
        return brand_chinese_text;
    }

    public void setBrand_chinese_text(String brand_chinese_text) {
        this.brand_chinese_text = brand_chinese_text;
    }

    public String getBrand_english_text() {
        return brand_english_text;
    }

    public void setBrand_english_text(String brand_english_text) {
        this.brand_english_text = brand_english_text;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }

    public Date getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(Date enter_time) {
        this.enter_time = enter_time;
    }

    public String getBrand_origin() {
        return brand_origin;
    }

    public void setBrand_origin(String brand_origin) {
        this.brand_origin = brand_origin;
    }

    public String getImport_type() {
        return import_type;
    }

    public void setImport_type(String import_type) {
        this.import_type = import_type;
    }

    public String getBrand_type() {
        return brand_type;
    }

    public void setBrand_type(String brand_type) {
        this.brand_type = brand_type;
    }

    public String getBrand_yieldly() {
        return brand_yieldly;
    }

    public void setBrand_yieldly(String brand_yieldly) {
        this.brand_yieldly = brand_yieldly;
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
