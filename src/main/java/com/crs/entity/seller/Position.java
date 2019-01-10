package com.crs.entity.seller;

import javax.persistence.*;

@Entity
@Table(name = "Position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "seller_id")
    @Column(name = "seller_id")
//    private Seller seller_id;
    private String seller_id;


    @Column(name = "province")
    private String province;//省

    @Column(name = "city")
    private String city;//市

    @Column(name = "region")
    private String region;//区

    @Column(name = "street")
    private String street;//街道

    @Column(name = "streetNumber")
    private String streetNumber;//街道

    @Column(name = "address")
    private String address;//商家地址

    @Column(name = "longitude")
    private double longitude;//经度

    @Column(name = "latitude")
    private double latitude;//纬度

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Seller getSeller() {
//        return seller;
//    }
//
//    public void setSeller(Seller seller) {
//        this.seller = seller;
//    }


    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
