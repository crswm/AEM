package com.crs.service.seller.impl;

import com.crs.dao.seller.SellerDao;
import com.crs.entity.seller.Seller;
import com.crs.service.seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao sellerDao;

    @Override
    public void add(Seller seller) {
        sellerDao.add(seller);
    }

    @Override
    public boolean update(Seller seller) {
        return sellerDao.update(seller);
    }

    @Override
    public boolean delete(Seller seller) {
        return sellerDao.delete(seller);
    }

    @Override
    public List<Seller> findSellers() {
        return sellerDao.findSellers();
    }

    @Override
    public List<Seller> findActiveSellers() {
        return sellerDao.findActiveSellers();
    }

    @Override
    public List<Seller> findByUserId(Integer id) {
        return sellerDao.findByUserId(id);
    }

    @Override
    public Seller findSeller(Integer id) {
        return sellerDao.findSeller(id);
    }
}
