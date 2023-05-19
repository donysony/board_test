package com.example.myproject.service;


import com.example.myproject.domain.Board;
import com.example.myproject.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    //spring 4.3이후 자동주입 처리 @AllArgsConstructor통해
    private BoardMapper mapper;

    @Override
    public boolean register(Board board) throws Exception {
        log.info("register...." + board);
        return mapper.insert(board);
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
    public int remove(Integer bno, String writer) {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return mapper.delete(map);
    }
    @Override
    public int removeAll(){
        return mapper.deleteAll();
    }

    @Override
    public List<Board> getList() {

        log.info("getList.....");
        return mapper.selectAll();

    }

    @Override
    public boolean increaseViewCnt(Integer bno) {

        log.info("count");
        return mapper.increaseViewCnt(bno) == 1;
    }
}
