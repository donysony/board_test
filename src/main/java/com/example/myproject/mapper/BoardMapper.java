package com.example.myproject.mapper;

import com.example.myproject.domain.Board;
import org.springframework.stereotype.Component;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Component
@MapperScan
public interface BoardMapper {
    public List<Board> getList();

    public void insert(Board board);
    public void insertSelectKey(Board board);

    public Board read(Long bno);
    public int delete(Long bno);
    public int update(Board board);

    public int increaseViewCnt(Long bno);
}
