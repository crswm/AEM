package com.crs.entity.format;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fdetail")
public class fdetail implements Serializable {
    private static final long serialVersionUID = -8595307554179439550L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fm_id")
    private int fm_id;

    @Column(name = "fdetail_name")
    private String fdetail_name;

    @Column(name = "dis_order")
    private int dis_order;

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

    public int getFm_id() {
        return fm_id;
    }

    public void setFm_id(int fm_id) {
        this.fm_id = fm_id;
    }

    public String getFdetail_name() {
        return fdetail_name;
    }

    public void setFdetail_name(String fdetail_name) {
        this.fdetail_name = fdetail_name;
    }

    public int getDis_order() {
        return dis_order;
    }

    public void setDis_order(int dis_order) {
        this.dis_order = dis_order;
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
