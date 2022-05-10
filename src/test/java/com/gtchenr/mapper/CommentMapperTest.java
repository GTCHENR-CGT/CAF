package com.gtchenr.mapper;

import com.gtchenr.pojo.Comment;
import com.gtchenr.utils.MybatisUtil;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class CommentMapperTest {
    CommentMapper commentMapper = MybatisUtil.getSqlSession().getMapper(CommentMapper.class);

    public void print(List<Comment> comments){
        for (Comment comment :comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void testComments(){
        List<Comment> comments = commentMapper.comments();
        print(comments);
    }

    @Test
    public void queryCommentByReportIdTest(){
        List<Comment> comments = commentMapper.queryCommentByReportId(1);
        print(comments);
    }
    @Test
    public void queryCommentByUserIdTest(){
        List<Comment> comments = commentMapper.queryCommentByUserId(1);
        print(comments);
    }

    @Test
    public void addTest(){

        Comment comment =new Comment();
        comment.setCommentDetails("这是一个测试2");
        comment.setUserId(1);
        comment.setReportId(1);
        comment.setCommentCredit(5);
        Date date = new Date();
        comment.setPublishTime(date);
        System.out.println(commentMapper.add(comment));
    }
}
