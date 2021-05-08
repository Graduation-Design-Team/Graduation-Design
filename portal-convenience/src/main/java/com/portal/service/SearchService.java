package com.portal.service;

import com.portal.pojo.ItemsSearch;

import java.util.List;

public interface SearchService {
    List<ItemsSearch> getListSearch();

    int insertItemsSearch(ItemsSearch itemsSearch);

    int updateStatusByAdmin(ItemsSearch itemsSearch);

    int updateStatusByUser(ItemsSearch itemsSearch);

    ItemsSearch getSearchById(Integer itemId);

    Integer updateSearch(ItemsSearch itemsSearch);

    Integer getNumWithDay();

    Integer getNumWithMonth();

    Integer getNumWithWeek();

    List<ItemsSearch> getSearchWithDay();

    List<ItemsSearch> getSearchWithWeek();

    List<ItemsSearch> getSearchWithMonth();

    List<ItemsSearch> getSearchDescByTime();

    List<ItemsSearch> getSearchSelf(Integer userId);
}
