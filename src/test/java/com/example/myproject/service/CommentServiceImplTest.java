package com.example.myproject.service;

import com.example.myproject.domain.Board;
import com.example.myproject.domain.Comment;
import com.example.myproject.mapper.BoardMapper;
import com.example.myproject.mapper.CommentMapper;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@Log4j
class CommentServiceImplTest {
    @Autowired
    CommentService service;
    @Autowired
    CommentMapper commentmapper;
    @Autowired
    BoardMapper boardmapper;



    @Test
    void getCommentList() {
       List list = service.getCommentList(2);
       list.forEach(s->log.info(s));
    }

    @Test
    void getCount() throws Exception {

        log.info(service.getCount(16));

    }

    @Test
    void remove() {
        int bno = 2;
        assertTrue(service.remove(bno));

    }

    @Test
    void removeComment() throws Exception {
        //bno가 존재하는지 체크,
        boardmapper.deleteAll();
        Board board = new Board("title","내용","어피치");
        assertTrue(boardmapper.insert(board));
        Integer bno = boardmapper.selectAll().get(0).getBno();
        System.out.println("bno : "+bno);


        Comment comment = new Comment(bno,1, "라이언", "카카오프렌즈");
        assertTrue(service.register(comment));
         int cno = commentmapper.selectAll(bno).get(0).getCno();
        System.out.println("cno : "+ cno);
        comment.setCno(cno);
        log.info("comment : "+comment);

        assertTrue(service.removeComment(comment));
    }

    @Test
    void register() {
        Comment comment = new Comment(17,1, "댓글", "무지");
        //댓글을 넣었다
        assertTrue(service.register(comment));
    }

    @Test
    void modify() {
        Comment comment = new Comment(15,"댓글내용123", "댓글작성자");
        assertTrue(service.modify(comment));
    }
    @Test
    void registerReComment(){
        //필요한 애들 (Integer pcno, Integer bno, String comment, String commenter, Integer cdep, Integer c_ref_order)
        Integer c_ref_order = commentmapper.selectCommentOrder(25) + 1;

        Comment comment = new Comment(25, 17,"나나1","무무1",1,c_ref_order);
        assertTrue(commentmapper.insertReComment(comment));
    }
}