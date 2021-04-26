package com.portal.service;

import com.portal.pojo.Cate;

import java.util.List;

public interface CateService {
    Integer addCate(Cate cate, Integer userId);

    List<Cate> cateList();

    Cate getCateById(Integer cateId);

    Integer deleteCateById(Integer cateId);

    Integer updateCate(Cate cate, Integer userId);

    Integer updateCatePicture(String path, Integer cateId, Integer userId);
}

