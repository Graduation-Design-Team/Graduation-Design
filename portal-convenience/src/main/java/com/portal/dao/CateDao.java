package com.portal.dao;


import com.portal.pojo.Cate;
import com.portal.pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CateDao {
    int deleteByPrimaryKey(Integer cateId);

    int insert(Cate record);

    int insertSelective(Cate record);

    Cate selectByPrimaryKey(Integer cateId);

    int updateByPrimaryKeySelective(Cate record);

    int updateByPrimaryKey(Cate record);

    List<Cate> selectAll();

    Integer updateCateStatus(Integer cateId);

    Integer updateNewsPicture(@Param("img") String path, Integer cateId);

    List<Cate> selectByTypeId(Integer typeId);

    List<Type> selectType();

    Integer insertType(String typeName);

    List<Cate> selectCateDescByReadcount();

    List<Cate> selectCateDescByTime();
}