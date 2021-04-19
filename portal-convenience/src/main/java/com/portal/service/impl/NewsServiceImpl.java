package com.portal.service.impl;

import com.portal.dao.NewsDao;
import com.portal.pojo.News;
import com.portal.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public Integer addNews(News news) {

        return newsDao.insertSelective(news);
    }

    @Override
    public List<News> newsList() {
        return newsDao.selectAll();
    }

    @Override
    public News getNewsById(Integer newsId) {
        return newsDao.selectByPrimaryKey(newsId);
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

}
