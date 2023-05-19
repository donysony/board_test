package com.example.myproject.service;

import com.example.myproject.domain.Board;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Log4j
class BoardServiceImplTest {
@Autowired
private BoardService service;
    @Test
    void register() throws Exception {
        Board board = new Board();
        board.setContent("내용");
        board.setTitle("제목");
        board.setWriter("라이언");

        assertTrue(service.register(board));

    }

    @Test
    void get() {

       // assertTrue(service.get(1L) );
    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }

    @Test
    void getList() {
        service.getList().forEach(board -> log.info(board));
    }

    @Test
    void increaseViewCnt() {
    }
}