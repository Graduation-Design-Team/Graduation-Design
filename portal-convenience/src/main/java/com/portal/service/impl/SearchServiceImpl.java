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
        //返回当前用户的发布寻物启事信息的条数
        int count = itemsSearchDao.getCount(itemsSearch.getUserId());
        //如果大于3，则不能继续发布
        if (count >= 3) {
            return 0;
        }

        return itemsSearchDao.insertItemsSearch(itemsSearch);
    }

    @Override
    public int updateStatusByAdmin(ItemsSearch itemsSearch) {
        return itemsSearchDao.updateStatusByAdmin(itemsSearch);
    }

    @Override
    public int updateStatusByUser(ItemsSearch itemsSearch) {
        return itemsSearchDao.updateStatusByUser(itemsSearch);
    }
}
