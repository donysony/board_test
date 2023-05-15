package com.example.myproject.service;

import com.example.myproject.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BoardService {
    public List<Board> getList();

    public Board get(Integer bno);
    public void register(Board board);
    public boolean modify(Board board);

    public boolean remove(Integer bno);
    public boolean increaseViewCnt(Integer bno);

}
