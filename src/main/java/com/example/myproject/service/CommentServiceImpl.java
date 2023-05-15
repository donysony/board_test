package com.example.myproject.service;

import com.example.myproject.domain.Comment;
import com.example.myproject.mapper.BoardMapper;
import com.example.myproject.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commapper;

//bno에 작성한 댓글 전체 조회
    @Override
    public List<Comment> getCommentList(Integer bno){
           return commapper.selectAll(bno);
    }
    // 댓글 수 조회
    @Override
    public int getCount(Integer bno) {
        return commapper.count(bno);
    }
//bno에 작성한 댓글 전체 삭제 -> bno를 삭제하면 함께 삭제되어야함
    @Override
    public boolean remove(@NotNull Comment comment){
        return commapper.deleteAll(comment.getBno());
    }
    @Override
    public boolean removeComment(Comment comment){
        return commapper.delete(comment);
    }

    @Override
    public boolean register(Comment comment){
        return commapper.insert(comment);
    }
    @Override
    public boolean modify(Comment comment){
        return commapper.update(comment) ;
    }


}
