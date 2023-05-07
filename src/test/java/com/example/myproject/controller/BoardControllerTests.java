/*
package com.example.myproject.controller;

import com.example.myproject.domain.Board;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j
public class BoardControllerTests {
    @Autowired
    private BoardController boardController;

    @Autowired
    private WebApplicationContext ctx; // 스프링 컨테이너

    private MockMvc mockMvc; // 가상의 톰캣

    */
/*public void setup(){
        ///MockMvc 객체를 초기화하고 테스트를 진행하도록 MockMvc를 초기화하는 메서드, 서버에 배포하지 않고도 Spring MVC의 동작 재현
        this.mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }*//*


    @BeforeEach // 매 테스트 이전에 해당 메소드 실행되도록 설정
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
    @Test
    public void testList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/board/list"))
                .andReturn()
                .getModelAndView()
                .getModelMap();
    }
}
*/
