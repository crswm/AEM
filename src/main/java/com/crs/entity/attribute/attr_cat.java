package com.crs.entity.attribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "attr_cat")
public class attr_cat implements Serializable {
    private static final long serialVersionUID = -1253261271365891523L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "relation_id")
    private int relation_id;

    @Column(name = "attr_id")
    private int attr_id;

    @Column(name = "c_id")
    private int c_id;

    @Column(name = "attr_type")
    private int c_iattr_typed;

    @Column(name = "attr_select_type")
    private int attr_select_type;

    @Column(name = "attr_group_id")
    private int attr_group_id;

    @Column(name = "option_type")
    private int option_type;

    @Column(name = "dis_order")
    private int dis_order;

    @Column(name = "enabled")
    private String enabled;

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

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public int getAttr_id() {
        return attr_id;
    }

    public void setAttr_id(int attr_id) {
        this.attr_id = attr_id;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getC_iattr_typed() {
        return c_iattr_typed;
    }

    public void setC_iattr_typed(int c_iattr_typed) {
        this.c_iattr_typed = c_iattr_typed;
    }

    public int getAttr_select_type() {
        return attr_select_type;
    }

    public void setAttr_select_type(int attr_select_type) {
        this.attr_select_type = attr_select_type;
    }

    public int getAttr_group_id() {
        return attr_group_id;
    }

    public void setAttr_group_id(int attr_group_id) {
        this.attr_group_id = attr_group_id;
    }

    public int getOption_type() {
        return option_type;
    }

    public void setOption_type(int option_type) {
        this.option_type = option_type;
    }

    public int getDis_order() {
        return dis_order;
    }

    public void setDis_order(int dis_order) {
        this.dis_order = dis_order;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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
