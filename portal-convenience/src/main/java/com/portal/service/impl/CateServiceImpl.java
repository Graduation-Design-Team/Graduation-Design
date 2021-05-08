package com.portal.service.impl;

import com.portal.dao.CateDao;
import com.portal.dao.UserDao;
import com.portal.pojo.Cate;
import com.portal.pojo.Type;
import com.portal.service.CateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateServiceImpl implements CateService {
    private static Logger logger = LoggerFactory.getLogger(CateServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private CateDao cateDao;

    @Override
    public Integer addCate(Cate cate, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法发布美食推荐，发布失败！！");
            return 0;
        }
        return cateDao.insertSelective(cate);
    }

    @Override
    public List<Cate> cateList() {
        return cateDao.selectAll();
    }

    @Override
    public Cate getCateById(Integer cateId) {
        Cate cate = cateDao.selectByPrimaryKey(cateId);
        cate.setReadCount(cate.getReadCount() + 1);
        cateDao.updateByPrimaryKeySelective(cate);
        return cate;
    }

    @Override
    public Integer deleteCateById(Integer cateId) {
        return cateDao.updateCateStatus(cateId);
    }

    @Override
    public Integer updateCate(Cate cate, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法修改m美食餐饮信息，修改失败！！");
            return 0;
        }
        return cateDao.updateByPrimaryKeySelective(cate);
    }

    @Override
    public Integer updateCatePicture(String path, Integer cateId, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法修改美食餐饮标题图片，修改失败！！");
            return 0;
        }
        return cateDao.updateNewsPicture(path, cateId);
    }

    @Override
    public List<Cate> getCatesByTypeId(Integer typeId) {
        return cateDao.selectByTypeId(typeId);
    }

    @Override
    public List<Type> getCateType() {
        return cateDao.selectType();
    }

    @Override
    public Integer addType(String typeName, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法添加菜系，添加失败！！");
            return 0;
        }
        return cateDao.insertType(typeName);
    }

    @Override
    public List<Cate> getCatesDescByReadcount() {
        return cateDao.selectCateDescByReadcount();
    }

    @Override
    public List<Cate> getCatesDescByTime() {
        return cateDao.selectCateDescByTime();
    }
}
