package com.gtchenr.mapper;

import com.gtchenr.pojo.Comment;
import org.elasticsearch.common.recycler.Recycler;

import java.util.List;

public interface CommentMapper {

    List<Comment> comments();

    List<Comment> queryCommentByReportId(Integer reportId);

    List<Comment> queryCommentByUserId(Integer userId);

    Integer add(Comment comment);
}
