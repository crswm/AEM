package com.crs.entity.attribute;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "value")
public class value implements Serializable {
    private static final long serialVersionUID = -6590604459075578827L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "tag_id")
    private int tag_id;

    @Column(name = "tag_text")
    private String tag_text;

    @Column(name = "attr_remark")
    private String attr_remark;

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

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_text() {
        return tag_text;
    }

    public void setTag_text(String tag_text) {
        this.tag_text = tag_text;
    }

    public String getAttr_remark() {
        return attr_remark;
    }

    public void setAttr_remark(String attr_remark) {
        this.attr_remark = attr_remark;
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
