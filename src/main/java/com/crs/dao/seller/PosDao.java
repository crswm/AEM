package com.crs.dao.seller;

import com.crs.entity.seller.Position;

import java.util.List;

public interface PosDao {
    void add(Position p);
    boolean update(Position p);
    boolean delete(Position p);
    List<Position> findPositions();
    List<Position> findBySellerId(Integer id);
    Position findPosition(Integer id);
}
