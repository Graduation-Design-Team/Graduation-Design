package com.portal.service.lmp;

import com.portal.mapper.positionDao;
import com.portal.pojo.Position;
import com.portal.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServicelmp implements PositionService {

    @Autowired
    private positionDao positiondao;

    @Override
    public Position showPositionById(Integer id) {
        return positiondao.showPositionById(id);
    }


    @Override
    public List<Position> showPositionAll() {
        return positiondao.showPositionAll();
    }

    @Override
    public List<Position> selectPositionFive(Position position) {
        return positiondao.selectPositionFive(position);
    }
}
