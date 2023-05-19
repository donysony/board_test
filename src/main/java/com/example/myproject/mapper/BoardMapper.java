package com.example.myproject.mapper;

import com.example.myproject.domain.Board;
import org.springframework.stereotype.Component;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@Component
@MapperScan
public interface BoardMapper {
    public List<Board> selectAll();

    public boolean insert(Board board) throws Exception;
    public void insertSelectKey(Board board);

    public Board read(Integer bno);
    public int delete(Map board);
    public int deleteAll();
    public int update(Board board);

    public int increaseViewCnt(Integer bno);
    public int updateCommentCnt(Integer bno);
}
