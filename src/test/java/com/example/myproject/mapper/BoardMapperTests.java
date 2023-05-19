package com.example.myproject.mapper;

import com.example.myproject.domain.Board;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;


@SpringBootTest
@Log4j
public class BoardMapperTests {
    @Autowired
    private BoardMapper mapper;


    @Test
    public void testGetList() {
        mapper.selectAll().forEach(board -> log.info(board));
    }

    @Test
    public void testDelete() throws Exception {
        mapper.deleteAll();

        Board board = new Board("title","content","writer");
        assertTrue(mapper.insert(board));
        Integer bno = mapper.selectAll().get(0).getBno();
        String writer = mapper.selectAll().get(0).getWriter();
        HashMap map = new HashMap();
        map.put("bno",bno);
        map.put("writer", writer);
        System.out.println("bno : "+ bno+"writer : "+writer);
        assertTrue(mapper.delete(map) == 1);
    }
}
