package com.example.myproject.controller;


import com.example.myproject.domain.Comment;
import com.example.myproject.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment/")
@Log4j
@AllArgsConstructor
public class CommentController {

    private CommentService service;

    // 댓글을 수정하는 메서드
    @PatchMapping("/comments/{cno}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody Comment comment) {
        String commenter = "asdf";
        comment.setCommenter(commenter);
        comment.setCno(cno);
        System.out.println("dto = " + comment);

        try {
            if(service.modify(comment))
                throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    //    {
//        "pcno":0,
//            "comment" : "hi"
//    }
    // 댓글을 등록하는 메서드
    @PostMapping("/create/{bno}")   // /comment/create/{bno} POST
    public ResponseEntity<String> write(@RequestBody Comment comment,@PathVariable Integer bno) {
//        String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        comment.setCommenter(commenter);
        comment.setBno(bno);
        System.out.println("dto = " + comment);

        try {
            if(service.register(comment))
                throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
//    @DeleteMapping("/comments/{cno}")  // DELETE /comments/1?bno=1085  <-- 삭제할 댓글 번호
//    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno) {
////        String commenter = (String)session.getAttribute("id");
//        String commenter = "asdf";
//
//        try {
//            int rowCnt = service.remove(cno, bno, commenter);
//
//            service.removeComment(comment);
//
//
//            if(rowCnt!=1)
//                throw new Exception("Delete Failed");
//
//            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
//        }
//    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments")  // /comments?bno=1080   GET
    public ResponseEntity<List<Comment>> list(Integer bno) {
        List<Comment> list = null;
        try {
            list = service.getCommentList(bno);
            return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);  // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Comment>>(HttpStatus.BAD_REQUEST); // 400
        }
    }


}