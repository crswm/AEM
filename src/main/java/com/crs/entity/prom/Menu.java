package com.crs.entity.prom;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "base_Menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -1116399204372824504L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "Menu_num")
    private int num;

    @Column(name = "Menu_parentId")
    private int parentId;

    @Column(name = "Menu_url")
    private String url;

    @Column(name = "Menu_name")
    private String name;

    @Column(name = "Menu_Order")
    private String menuOrder;

    @Column(name = "Menu_descrition")
    private String descrition;

    @Column(name = "level")
    private int level;

    @Transient
    private boolean hasSubMenu= false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public boolean isHasSubMenu() {
        return hasSubMenu;
    }

    public void setHasSubMenu(boolean hasSubMenu) {
        this.hasSubMenu = hasSubMenu;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Menu [num=" + num + ", pid=" + parentId + ",url=" + url + "]";
    }
}
