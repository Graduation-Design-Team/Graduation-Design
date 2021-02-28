package com.portal.mapper;


import com.portal.pojo.Position;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface positionDao {

    Position showPositionById(Integer id);

    List<Position> showPositionAll();

    List<Position> selectPositionFive(Position position);

}
