package com.portal.dao;


import com.portal.pojo.Comment;

import java.util.List;

public interface CommentDao {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    Integer updateStatus(Integer commentId);

    List<Comment> selectAll();

    List<Comment> selectCommentWithDeleted();

    Integer updateStatus1(Integer commentId);
}