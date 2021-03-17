package com.portal.dao;

import com.portal.pojo.ItemsSearch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsSearchDao {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemsSearch record);

    int insertSelective(ItemsSearch record);

    ItemsSearch selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(ItemsSearch record);

    int updateByPrimaryKey(ItemsSearch record);

    List<ItemsSearch> selectIemsSearch();

    int insertItemsSearch(ItemsSearch itemsSearch);
}