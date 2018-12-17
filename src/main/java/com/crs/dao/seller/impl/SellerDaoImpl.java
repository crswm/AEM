package com.crs.dao.seller.impl;

import com.crs.dao.impl.BaseDaoImpl;
import com.crs.dao.seller.SellerDao;
import com.crs.entity.seller.Seller;
import org.hibernate.Query;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Resource
public class SellerDaoImpl extends BaseDaoImpl implements SellerDao {


    @Override
    public void add(Seller seller) {
        super.add(seller);
    }

    @Override
    public boolean update(Seller seller) {

        return super.editEntity(seller);
    }

    @Override
    public boolean delete(Seller seller) {
        return super.removeEntity(seller);
    }

    @Override
    public List<Seller> findSellers() {
        String hql = "from Seller where 1=1";
        return super.findByHql(hql);
    }
    @Override
    public List<Seller> findActiveSellers() {
        String hql = "from Seller where is_del=0 and is_work =1";
        return super.findByHql(hql);
    }

    @Override
    public List<Seller> findByUserId(Integer id) {
        String hql = " from Seller where userId="+id;
        List<Seller> sellers = new ArrayList<Seller>();
        Query query = super.getSession().createQuery(hql);
        sellers = query.list();
        return sellers;
    }

    @Override
    public Seller findSeller(Integer id) {
        return super.getById(Seller.class,id);
    }
}
