package com.example.myproject.service;

import com.example.myproject.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BoardService {
    public List<Board> getList();

    public Board get(Integer bno);
    public boolean register(Board board) throws Exception;
    public boolean modify(Board board);

    public int remove(Integer bno, String writer) throws Exception;
    public int removeAll();
    public boolean increaseViewCnt(Integer bno);

}
