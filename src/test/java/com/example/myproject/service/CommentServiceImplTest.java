package com.example.myproject.service;

import com.example.myproject.domain.Board;
import com.example.myproject.domain.Comment;
import com.example.myproject.mapper.BoardMapper;
import com.example.myproject.mapper.CommentMapper;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j
class CommentServiceImplTest {
    @Autowired
    CommentService service;
    @Autowired
    CommentMapper commentmapper;


    @Test
    void getCommentList() {
       List list = service.getCommentList(2);
       list.forEach(s->log.info(s));
    }

    @Test
    void getCount() throws Exception {
        int bno = 2;
        Comment comment = new Comment(bno,1, "댓글내용", "댓글작성자");
        //댓글을 넣었다
        commentmapper.insert(comment);
        service.getCount(bno);

    }

    @Test
    void remove() {
    }

    @Test
    void removeComment() {
    }

    @Test
    void register() {
    }

    @Test
    void modify() {
    }
}