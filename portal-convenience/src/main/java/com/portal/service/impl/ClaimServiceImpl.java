package com.portal.service.impl;

import com.portal.dao.ItemsClaimDao;
import com.portal.dao.UserDao;
import com.portal.pojo.ItemsClaim;
import com.portal.service.ClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {
    private static Logger logger = LoggerFactory.getLogger(ClaimServiceImpl.class);
    @Autowired
    private ItemsClaimDao itemsClaimDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<ItemsClaim> getListClaim() {
        return itemsClaimDao.selectItemsClaim();
    }

    @Override
    public int insertItemsClaim(ItemsClaim itemsClaim) {
        //返回当前用户的发布寻物启事信息的条数
        int count = itemsClaimDao.getCount(itemsClaim.getUserId());
        //如果大于3，则不能继续发布
        if (count >= 3) {
            return 0;
        }

        return itemsClaimDao.insertItemsClaim(itemsClaim);
    }

    @Override
    public int updateStatusByAdmin(ItemsClaim itemsClaim) {
        Integer userId = itemsClaim.getUserId();
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("您不是管理员，无权限");
            return 0;
        }
        return itemsClaimDao.updateStatusByAdmin(itemsClaim);
    }

    @Override
    public int updateStatusByUser(ItemsClaim itemsClaim) {
        return itemsClaimDao.updateStatusByUser(itemsClaim);
    }

    @Override
    public ItemsClaim getClaimById(Integer itemId) {
        return itemsClaimDao.selectByPrimaryKey(itemId);
    }

    @Override
    public Integer updateSearch(ItemsClaim itemsClaim) {
        return itemsClaimDao.updateByPrimaryKeySelective(itemsClaim);
    }

    @Override
    public Integer getNumWithDay() {
        return itemsClaimDao.selectNumWithDay();
    }

    @Override
    public Integer getNumWithWeek() {
        return itemsClaimDao.selectNumWithWeek();
    }

    @Override
    public Integer getNumWithMonth() {
        return itemsClaimDao.selectNumWithMonth();
    }

    @Override
    public List<ItemsClaim> getClaimWithDay() {
        return itemsClaimDao.selectClaimWithDay();
    }

    @Override
    public List<ItemsClaim> getClaimWithWeek() {
        return itemsClaimDao.selectClaimWithWeek();
    }

    @Override
    public List<ItemsClaim> getClaimWithMonth() {
        return itemsClaimDao.selectClaimWithMonth();
    }

    @Override
    public List<ItemsClaim> getClaimDescByTime() {
        return itemsClaimDao.selectClaimDescByTime();
    }

    @Override
    public List<ItemsClaim> getClaimSelf(Integer userId) {
        return itemsClaimDao.selectClaimSelf(userId);
    }
}
