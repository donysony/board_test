package com.example.myproject.mapper;

import com.example.myproject.domain.Board;
import org.springframework.stereotype.Component;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Component
@MapperScan
public interface BoardMapper {
    public List<Board> getList();

    public boolean insert(Board board) throws Exception;
    public void insertSelectKey(Board board);

    public Board read(Integer bno);
    public int delete(Integer bno);
    public int update(Board board);

    public int increaseViewCnt(Integer bno);
    public int updateCommentCnt(Integer bno);
}
