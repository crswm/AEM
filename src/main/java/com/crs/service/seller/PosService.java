package com.crs.service.seller;

import com.crs.entity.seller.Position;

import java.util.List;

public interface PosService {
    void add(Position p);
    boolean update(Position p);
    boolean delete(Position p);
    List<Position> findPositions();
    List<Position> findBySellerId(Integer id);
    Position findPosition(Integer id);
}
