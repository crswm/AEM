package com.crs.entity.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "goods_format")
public class goods_format implements Serializable {
    private static final long serialVersionUID = -4741933272718467790L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "format_type")
    private int  format_type;

    @Column(name = "format_detail")
    private int  format_detail;

    @Column(name = "format_value")
    private int  format_value;

    @Column(name = "sku_id")
    private int  sku_id;

    @Column(name = "enabled")
    private Boolean  enabled;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "update_date")
    private Date  update_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormat_type() {
        return format_type;
    }

    public void setFormat_type(int format_type) {
        this.format_type = format_type;
    }

    public int getFormat_detail() {
        return format_detail;
    }

    public void setFormat_detail(int format_detail) {
        this.format_detail = format_detail;
    }

    public int getFormat_value() {
        return format_value;
    }

    public void setFormat_value(int format_value) {
        this.format_value = format_value;
    }

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }
}
