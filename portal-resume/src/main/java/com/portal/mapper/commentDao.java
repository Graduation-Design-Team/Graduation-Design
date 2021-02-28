package com.portal.mapper;


import com.portal.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface commentDao {

    List<Comment> showCommentByPosition(Integer pageNum,Integer pageSize);

    List<Comment> showCommentByUsers(Integer pageNum,Integer pageSize);

    List<Comment> outsideURL(Integer pageNum,Integer pageSize);

}
