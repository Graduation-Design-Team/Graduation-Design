package com.portal.service;

import com.portal.pojo.Comment;
import com.portal.pojo.News;

import java.util.List;

public interface NewsService {


    Integer addNews(News news, Integer userId);

    List<News> newsList();

    News getNewsById(Integer newsId);

    Integer deleteNewsById(Integer newsId);

    Integer updateNews(News news, Integer userId);

    Integer updateNewsPicture(String path, Integer newsId, Integer userId);

    Integer addComment(Integer newsId, Integer userId, String content);

    Integer updateComment(Integer commentId, Integer newsId, Integer userId, String content);

    Integer deleteCommentById(Integer commentId, Integer userId);

    Integer updateStatus(Integer commentId, Integer userId);

    List<Comment> selectComment();

    List<Comment> selectCommentWithDeleted(Integer userId);

    Integer updateStatus1(Integer commentId, Integer userId);

    Integer recoverNewsById(Integer newsId);

    List<News> selectNewsWithDelted(Integer userId);
}
