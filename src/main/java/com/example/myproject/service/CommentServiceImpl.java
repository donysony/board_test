package com.example.myproject.service;

import com.example.myproject.domain.Comment;
import com.example.myproject.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    @Override
    public List<Comment> getReCommentList(Integer bno,Integer cno ){

        HashMap<String, Integer> map = new HashMap();
        map.put("bno", bno);
        map.put("cno", cno);

        return commapper.selectReCommentAll(map);
    }
    // 댓글 수 조회
    @Override
    public int getCount(Integer bno) {
        return commapper.count(bno);
    }
//bno에 작성한 댓글 전체 삭제 -> bno를 삭제하면 함께 삭제되어야함
    @Override
    public boolean remove(Integer bno){
        return commapper.deleteAll(bno);
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


    public boolean registerReComment(Comment comment){
        //대댓글의 cdep를 1로 설정
        comment.setCdep(1);

        int c_ref_order = commapper.selectCommentOrder(comment.getCno()) + 1;
        comment.setC_ref_order(c_ref_order);
        return commapper.insertReComment(comment);
    };

}
