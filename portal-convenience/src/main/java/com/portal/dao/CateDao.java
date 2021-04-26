package com.portal.dao;


import com.portal.pojo.Cate;
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
}