package com.crs.service.seller;

import com.crs.entity.seller.Seller;

import java.util.List;

public interface SellerService {
    void add(Seller seller);
    boolean update(Seller seller);
    boolean delete(Seller seller);
    List<Seller> findSellers();
    List<Seller> findActiveSellers();
    List<Seller> findByUserId(Integer id);
    Seller findSeller(Integer id);
}
