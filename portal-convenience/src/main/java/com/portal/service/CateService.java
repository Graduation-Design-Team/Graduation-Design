package com.portal.service;

import com.portal.pojo.Cate;
import com.portal.pojo.Type;

import java.util.List;

public interface CateService {
    Integer addCate(Cate cate, Integer userId);

    List<Cate> cateList();

    Cate getCateById(Integer cateId);

    Integer deleteCateById(Integer cateId);

    Integer updateCate(Cate cate, Integer userId);

    Integer updateCatePicture(String path, Integer cateId, Integer userId);

    List<Cate> getCatesByTypeId(Integer typeId);

    List<Type> getCateType();

    Integer addType(String typeName, Integer userId);

    List<Cate> getCatesDescByReadcount();

    List<Cate> getCatesDescByTime();
}

