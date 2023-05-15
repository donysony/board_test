package com.example.myproject.service;


import com.example.myproject.domain.Board;
import com.example.myproject.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    //spring 4.3이후 자동주입 처리 @AllArgsConstructor통해
    private BoardMapper mapper;

    @Override
    public void register(Board board) {
        log.info("register...." + board);
        mapper.insertSelectKey(board);
    }

    @Override
    public Board get(Integer bno) {
        log.info("get......" + bno);

        return mapper.read(bno);
    }

    @Override
    public boolean modify(Board board) {
        log.info("modify..." + board);
        return mapper.update(board) == 1;

    }

    @Override
    public boolean remove(Integer bno) {
        log.info("remove..." + bno);
        return mapper.delete(bno) == 1;

    }

    @Override
    public List<Board> getList() {

        log.info("getList.....");
        return mapper.getList();

    }

    @Override
    public boolean increaseViewCnt(Integer bno) {

        log.info("count");
        return mapper.increaseViewCnt(bno) == 1;
    }
}
