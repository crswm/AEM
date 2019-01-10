package com.crs.dao.seller.impl;

import com.crs.dao.impl.BaseDaoImpl;
import com.crs.dao.seller.PosDao;
import com.crs.entity.seller.Position;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

public class PosDaoImpl extends BaseDaoImpl implements PosDao {

    @Override
    public void add(Position p) {
        super.add(p);
    }

    @Override
    public boolean update(Position p) {
        return  super.editEntity(p);
    }

    @Override
    public boolean delete(Position p) {
        return super.removeEntity(p);
    }

    @Override
    public List<Position> findPositions() {
        String hql = "from Position where 1=1";
        return super.findByHql(hql);
    }

    @Override
    public List<Position> findBySellerId(Integer id) {
        String hql = " from Position where seller_id="+id;
//        List<Position> ps = new ArrayList<Position>();
//        Query query = super.getSession().createQuery(hql);
//        ps = query.list();
//        return ps;
        return super.findByHql(hql);

    }

    @Override
    public Position findPosition(Integer id) {
       return super.getById(Position.class,id);
    }
}
