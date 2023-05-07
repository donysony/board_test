package com.example.myproject.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j
public class BoardMapperTests {
    @Autowired
    private BoardMapper mapper;


    @Test
    public void testGetList() {
        mapper.getList().forEach(board -> log.info(board));
    }
}
