package com.example.myproject.service;

import com.example.myproject.domain.Board;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BoardService {
    public List<Board> getList();

    public Board get(Long bno);
    public void register(Board board);
    public boolean modify(Board board);

    public boolean remove(Long bno);
    public boolean increaseViewCnt(Long bno);
}
