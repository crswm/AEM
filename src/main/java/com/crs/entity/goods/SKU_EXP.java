package com.crs.entity.goods;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SKU_EXP")
public class SKU_EXP implements Serializable {
    private static final long serialVersionUID = -8868122392429236156L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "sku_id")
    private int  sku_id;

    @Column(name = "goods_length")
    private float  goods_length;

    @Column(name = "goods_width")
    private float  goods_width;

    @Column(name = "goods_volume")
    private float  goods_volume;

    @Column(name = "goods_weight")
    private float  goods_weight;

    @Column(name = "guarantee_period")
    private int  guarantee_period;//保质期

    @Column(name = "goods_airlift")
    private Boolean  goods_airlift;//是否空运

    @Column(name = "goods_flip")
    private Boolean  goods_flip;

    @Column(name = "goods_nature")
    private int  goods_nature;

    @Column(name = "sale_price")
    private double  sale_price;

    @Column(name = "market_price")
    private double  market_price;

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

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public float getGoods_length() {
        return goods_length;
    }

    public void setGoods_length(float goods_length) {
        this.goods_length = goods_length;
    }

    public float getGoods_width() {
        return goods_width;
    }

    public void setGoods_width(float goods_width) {
        this.goods_width = goods_width;
    }

    public float getGoods_volume() {
        return goods_volume;
    }

    public void setGoods_volume(float goods_volume) {
        this.goods_volume = goods_volume;
    }

    public float getGoods_weight() {
        return goods_weight;
    }

    public void setGoods_weight(float goods_weight) {
        this.goods_weight = goods_weight;
    }

    public int getGuarantee_period() {
        return guarantee_period;
    }

    public void setGuarantee_period(int guarantee_period) {
        this.guarantee_period = guarantee_period;
    }

    public Boolean getGoods_airlift() {
        return goods_airlift;
    }

    public void setGoods_airlift(Boolean goods_airlift) {
        this.goods_airlift = goods_airlift;
    }

    public Boolean getGoods_flip() {
        return goods_flip;
    }

    public void setGoods_flip(Boolean goods_flip) {
        this.goods_flip = goods_flip;
    }

    public int getGoods_nature() {
        return goods_nature;
    }

    public void setGoods_nature(int goods_nature) {
        this.goods_nature = goods_nature;
    }

    public double getSale_price() {
        return sale_price;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(double market_price) {
        this.market_price = market_price;
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
