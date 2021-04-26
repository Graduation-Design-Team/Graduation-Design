package com.portal.service.impl;

import com.portal.dao.NewsDao;
import com.portal.dao.UserDao;
import com.portal.pojo.News;
import com.portal.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
    @Autowired
    private NewsDao newsDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Integer addNews(News news, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法发布新闻，发布失败！！");
            return 0;
        }
        return newsDao.insertSelective(news);
    }

    @Override
    public List<News> newsList() {
        return newsDao.selectAll();
    }

    @Override
    public News getNewsById(Integer newsId) {
        News news = newsDao.selectByPrimaryKey(newsId);
        news.setReadCount(news.getReadCount() + 1);
        newsDao.updateByPrimaryKeySelective(news);
        return news;
    }


    /**
     * 根据newsId逻辑删除该条新闻
     *
     * @param newsId
     */
    @Override

    public Integer deleteNewsById(Integer newsId) {
        return newsDao.updateNewsStatus(newsId);
    }

    @Override
    public Integer updateNews(News news, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法修改新闻，修改失败！！");
            return 0;
        }
        return newsDao.updateByPrimaryKeySelective(news);
    }

    @Override
    public Integer updateNewsPicture(String path, Integer newsId, Integer userId) {
        Integer roleId = userDao.getRoleIdByUserId(userId);
        if (roleId != 5) {
            logger.warn("你不是管理员，无法修改新闻标题图片，修改失败！！");
            return 0;
        }
        return newsDao.updateNewsPicture(path, newsId);
    }

}
