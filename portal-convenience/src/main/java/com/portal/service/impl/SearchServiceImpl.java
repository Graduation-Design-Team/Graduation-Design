package com.portal.service.impl;

import com.portal.dao.ItemsSearchDao;
import com.portal.pojo.ItemsSearch;
import com.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ItemsSearchDao itemsSearchDao;

    @Override
    public List<ItemsSearch> getListSearch() {
        return itemsSearchDao.selectIemsSearch();
    }

    @Override
    public int insertItemsSearch(ItemsSearch itemsSearch) {


        return itemsSearchDao.insertItemsSearch(itemsSearch);
    }

    @Override
    public int updateStatusByAdmin(ItemsSearch itemsSearch) {
        return itemsSearchDao.updateStatusByAdmin(itemsSearch);
    }
}
