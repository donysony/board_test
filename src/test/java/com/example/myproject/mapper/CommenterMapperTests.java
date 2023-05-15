package com.example.myproject.mapper;

import com.example.myproject.domain.Comment;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@Log4j
public class CommenterMapperTests {
    @Autowired
    private CommentMapper mapper;

    @Test
    public void selectAll(){
        Comment comment = new Comment(2, 2, "댓글~", "오리");  //bno, pcno,comment, commenter
        mapper.insert(comment);
        List<Comment> list = mapper.selectAll(2);
        list.forEach(s->log.info(s));
    }
    @Test
    public void deleteAll(){
    mapper.deleteAll(2);
    }

    @Test
    public void delete(){
        Comment comment = new Comment( 3,"오리");
    mapper.delete(comment);
    }
    @Test
    public void count(){
        log.info("result : "+mapper.count(2));
    }
    @Test
    public void update(){
        Comment comment = new Comment( 4,"오리", "수정내용");
//문제 : cno가 없는데 값이 들어감
//문제 : cno랑 commenter가 다른데 true결과 나옴
//cno와 commenter가 일치하는 내용 입력시 정상동작
        mapper.update(comment);
    }
    @Test
    public void insert(){
        int bno = 2;
        Comment comment = new Comment(bno,1, "댓글내용", "댓글작성자");
        //댓글을 넣었다
        mapper.insert(comment);
    }




}
