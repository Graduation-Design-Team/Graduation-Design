package com.portal.dao;


import com.portal.pojo.ItemsClaim;

import java.util.List;

public interface ItemsClaimDao {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemsClaim record);

    int insertSelective(ItemsClaim record);

    ItemsClaim selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(ItemsClaim record);

    int updateByPrimaryKey(ItemsClaim record);

    List<ItemsClaim> selectItemsClaim();

    int getCount(Integer userId);

    int insertItemsClaim(ItemsClaim itemsClaim);

    int updateStatusByAdmin(ItemsClaim itemsClaim);

    int updateStatusByUser(ItemsClaim itemsClaim);

    Integer selectNumWithDay();

    Integer selectNumWithWeek();

    Integer selectNumWithMonth();

    List<ItemsClaim> selectClaimWithDay();

    List<ItemsClaim> selectClaimWithWeek();

    List<ItemsClaim> selectClaimWithMonth();

    List<ItemsClaim> selectClaimDescByTime();

    List<ItemsClaim> selectClaimSelf(Integer userId);
}