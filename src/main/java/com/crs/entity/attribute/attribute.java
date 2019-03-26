package com.crs.entity.attribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "attribute")
public class attribute implements Serializable {
    private static final long serialVersionUID = -3908044415459547302L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "attr_text")
    private String attr_text;

    @Column(name = "attr_remark")
    private String attr_remark;

    @Column(name = "attr_input_type")
    private String attr_input_type;

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

    public String getAttr_text() {
        return attr_text;
    }

    public void setAttr_text(String attr_text) {
        this.attr_text = attr_text;
    }

    public String getAttr_remark() {
        return attr_remark;
    }

    public void setAttr_remark(String attr_remark) {
        this.attr_remark = attr_remark;
    }

    public String getAttr_input_type() {
        return attr_input_type;
    }

    public void setAttr_input_type(String attr_input_type) {
        this.attr_input_type = attr_input_type;
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
