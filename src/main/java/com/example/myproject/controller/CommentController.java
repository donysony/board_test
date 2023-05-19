package com.example.myproject.controller;


import com.example.myproject.domain.Comment;
import com.example.myproject.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController -> 가 있는 클래스는 @ResponseBody가 붙은 메서드로 간주함
//@ResponseBody를 사용한 메서드가 여러개일 경우
//Controller 이 두개를 묶은게 @RestController
@Controller
@Log4j
@AllArgsConstructor
public class CommentController {

    private CommentService service;
    //댓글 테스트
    @GetMapping("/test")
    public String test(){return "board/commentTest";}


    // 댓글을 수정하는 메서드
    @PatchMapping("comments/{cno}")   // /ch4/comments/26  PATCH
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody Comment comment) {

        Comment comment1 = new Comment(cno, comment.getComment(), comment.getCommenter()  );
        System.out.println("patch_dto = " + comment1);

        try {
            if(!service.modify(comment1))
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
    @PostMapping("comments/{bno}")   // /comment/{bno} POST
    public ResponseEntity<Integer> write(@RequestBody Comment comment, @PathVariable Integer bno, RedirectAttributes rttr) {

        //댓글 등록 cdep : 0,
        String commenter = "어피치";
        comment.setCommenter(commenter);
        System.out.println("댓글등록 = " + comment);

        try {
            if(!service.register(comment))
                throw new Exception("Write failed.");
            rttr.addFlashAttribute("msg","WRT_OK");
            return new ResponseEntity<>(bno, HttpStatus.OK); //상태코드 커스텀
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(bno, HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")  // DELETE /comments/1?bno=1085  <-- 삭제할 댓글 번호
    public ResponseEntity<Integer> remove(@PathVariable Integer cno, @RequestBody Comment comment) {
//        String commenter = (String)session.getAttribute("id");
        //작성자의 id의 가져와 사용해주기!
        //String commenter = "asdf";
        System.out.println("comment : "+comment);
        comment.setCno(cno);
        System.out.println("del_dto = " + comment);
        Integer bno = comment.getBno();


        try {

            if(!service.removeComment(comment))
                throw new Exception("Delete Failed");

            return new ResponseEntity<>(bno, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(bno, HttpStatus.BAD_REQUEST);
        }
    }


    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments")  // /comments?bno=1080   GET
    @ResponseBody
    public ResponseEntity<List<Comment>> list(Integer bno) {
        List<Comment> list = null;

        System.out.println("get comments list 실행");
        try {

            list = service.getCommentList(bno); // bno에 맞는 댓글 리스트 가져오기

            return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);  // 200 ResponseEntity(T body, HttpStatusCode status)
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Comment>>(HttpStatus.BAD_REQUEST); // 400
        }
    }
    // 하위댓글 가져오기
    @GetMapping("/comments/{cno}") // /comments/25 GET
    @ResponseBody
    public ResponseEntity<List<Comment>> List(Integer bno, @PathVariable Integer cno){
        List<Comment> list = null;
        System.out.println("하위댓글 가져오기 = " + "하위댓글 가져오기");
        try {
            list = service.getReCommentList(bno, cno); // bno의 게시물 중 cno와 pcno가 일치하는 댓글 리스트 가져오기

            return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);  // 200 ResponseEntity(T body, HttpStatusCode status)
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Comment>>(HttpStatus.BAD_REQUEST); // 400
        }
    }


}