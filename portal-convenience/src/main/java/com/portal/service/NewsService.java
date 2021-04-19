package com.portal.service;

import com.portal.pojo.News;

import java.util.List;

public interface NewsService {


    Integer addNews(News news);

    List<News> newsList();

    News getNewsById(Integer newsId);

    Integer deleteNewsById(Integer newsId);
}
