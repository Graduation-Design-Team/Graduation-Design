package com.portal.dao;

import com.portal.pojo.ItemsClaim;

public interface ItemsClaimDao {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemsClaim record);

    int insertSelective(ItemsClaim record);

    ItemsClaim selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(ItemsClaim record);

    int updateByPrimaryKey(ItemsClaim record);
}