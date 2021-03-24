package com.portal.service;

import com.portal.pojo.ItemsSearch;

import java.util.List;

public interface SearchService {
    List<ItemsSearch> getListSearch();

    int insertItemsSearch(ItemsSearch itemsSearch);

    int updateStatusByAdmin(ItemsSearch itemsSearch);
}
