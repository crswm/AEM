package com.crs.service.seller.impl;

import com.crs.dao.seller.PosDao;
import com.crs.entity.seller.Position;
import com.crs.service.seller.PosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PosServiceImpl implements PosService {

    @Autowired
    private PosDao posDao;

    @Override
    public void add(Position p) {
        posDao.add(p);
    }

    @Override
    public boolean update(Position p) {
        return posDao.update(p);
    }

    @Override
    public boolean delete(Position p) {
        return posDao.delete(p);
    }

    @Override
    public List<Position> findPositions() {
        return posDao.findPositions();
    }

    @Override
    public List<Position> findBySellerId(Integer id) {
        return posDao.findBySellerId(id);
    }

    @Override
    public Position findPosition(Integer id) {
        return posDao.findPosition(id);
    }
}
