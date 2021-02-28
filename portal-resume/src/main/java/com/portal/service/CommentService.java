package com.portal.service;

import com.portal.pojo.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> showCommentByPosition(Integer pageNum, Integer pageSize);

    List<Comment> showCommentByUsers(Integer pageNum,Integer pageSize);

    List<Comment> outsideURL(Integer pageNum,Integer pageSize);
}
