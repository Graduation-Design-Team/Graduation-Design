package com.portal.dao;


import com.portal.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {
    int deleteByPrimaryKey(Integer newsId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectAll();

    Integer updateNewsStatus(Integer newsId);

    Integer updateNewsPicture(@Param("img") String path, Integer newsId);
}