package com.crs.entity.classify;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "f_classify_attr_value")
public class f_classify_attr_value implements Serializable {
    private static final long serialVersionUID = 6635748689722576448L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "relation_id")
    private int relation_id;

    @Column(name = "c_id")
    private int c_id;

    @Column(name = "show_mark")
    private int show_mark;

    @Column(name = "dis_order")
    private int dis_order;

    @Column(name = "tag_link_id")
    private int tag_link_id;

    @Column(name = "tag_name")
    private String tag_name;

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

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getShow_mark() {
        return show_mark;
    }

    public void setShow_mark(int show_mark) {
        this.show_mark = show_mark;
    }

    public int getDis_order() {
        return dis_order;
    }

    public void setDis_order(int dis_order) {
        this.dis_order = dis_order;
    }

    public int getTag_link_id() {
        return tag_link_id;
    }

    public void setTag_link_id(int tag_link_id) {
        this.tag_link_id = tag_link_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
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