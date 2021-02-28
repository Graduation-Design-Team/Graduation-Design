package com.portal.service;

import com.portal.pojo.Position;

import java.util.List;

public interface PositionService {

    Position showPositionById(Integer id);

    List<Position> showPositionAll();

    List<Position> selectPositionFive(Position position);
}
