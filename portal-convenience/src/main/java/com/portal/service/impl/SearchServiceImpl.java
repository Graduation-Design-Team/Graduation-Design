package com.portal.service.impl;

import com.portal.dao.ItemsSearchDao;
import com.portal.dao.UserDao;
import com.portal.pojo.ItemsSearch;
import com.portal.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    @Autowired
    private ItemsSearchDao itemsSearchDao;
    @Autowired
    private UserDao userDao;


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
        Integer userId = itemsSearch.getUserId();
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("您不是管理员，无权限");
            return 0;
        }

        return itemsSearchDao.updateStatusByAdmin(itemsSearch);
    }

    @Override
    public int updateStatusByUser(ItemsSearch itemsSearch) {
        return itemsSearchDao.updateStatusByUser(itemsSearch);
    }

    @Override
    public ItemsSearch getSearchById(Integer itemId) {
        return itemsSearchDao.selectByPrimaryKey(itemId);
    }

    @Override
    public Integer updateSearch(ItemsSearch itemsSearch) {
        return itemsSearchDao.updateByPrimaryKeySelective(itemsSearch);
    }

    @Override
    public Integer getNumWithDay() {
        return itemsSearchDao.selectNumWithDay();
    }

    @Override
    public Integer getNumWithWeek() {
        return itemsSearchDao.selectNumWithWeek();
    }

    @Override
    public Integer getNumWithMonth() {
        return itemsSearchDao.selectNumWithMonth();
    }

    @Override
    public List<ItemsSearch> getSearchWithDay() {
        return itemsSearchDao.selectSearchWithDay();
    }

    @Override
    public List<ItemsSearch> getSearchWithWeek() {
        return itemsSearchDao.selectSearchWithWeek();
    }

    @Override
    public List<ItemsSearch> getSearchWithMonth() {
        return itemsSearchDao.selectSearchWithMonth();
    }

    @Override
    public List<ItemsSearch> getSearchDescByTime() {
        return itemsSearchDao.selectSearchDescByTime();
    }

    @Override
    public List<ItemsSearch> getSearchSelf(Integer userId) {
        return itemsSearchDao.selectSearchSelf(userId);
    }


}
