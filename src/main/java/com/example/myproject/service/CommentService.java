package com.example.myproject.service;

import com.example.myproject.domain.Comment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    //bno에 작성한 댓글 전체 조회
    List<Comment> getCommentList(Integer bno);

    // 댓글 수 조회
    int getCount(Integer bno);

    //bno에 작성한 댓글 전체 삭제 -> bno를 삭제하면 함께 삭제되어야함
    boolean remove(Comment comment);

    boolean removeComment(Comment comment);

    boolean register(Comment comment);

    boolean modify(Comment comment);
}
