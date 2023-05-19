package com.example.myproject.mapper;

import com.example.myproject.domain.Comment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@MapperScan
public interface CommentMapper {
//댓글수
    public int count(Integer bno);
//댓글을 해당 게시물에 등록
    public boolean insert(Comment comment);
//게시물번호를 조건절로 하여 댓글을 전부 가져옴
    public List<Comment> selectAll(Integer bno);
    //댓글의 cno번호를 참조하여 대댓글을 전부 가져옴
    public List<Comment> selectReCommentAll(Map map);
    //게시글번호에 맞는 댓글 전부 삭제
    public boolean deleteAll(Integer bno);
    //댓글 중 cno와 commenter가 같은 댓글 삭제
    public boolean delete(Comment comment);
    //댓글 내용 수정
    public boolean update(Comment comment);
    //댓글 정렬순서위한 메소드
    public int selectCommentOrder(Integer cno);
    //대댓글 등록
    public boolean insertReComment(Comment comment);
}