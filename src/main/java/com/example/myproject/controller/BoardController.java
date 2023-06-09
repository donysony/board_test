package com.example.myproject.controller;

import com.example.myproject.domain.Board;
import com.example.myproject.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Log4j
@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    //BoardController은 BoardService에 의존적이므로 @AllArgsConstructor이용해 생성자 자동 주입
    private BoardService service;

    @GetMapping("/list")
    public String list(Model model) {

        log.info("list");
        List<Board> list = service.getList();
        model.addAttribute("list",list );
        return "board/list";
    }

    @GetMapping("/register")
    public String register(){
        //입력페이지를 보여주는 역할
        return "board/register";
    }

    @PostMapping("/register")
    public String register(Board board, Model m, RedirectAttributes rttr) {
        try{
            if(!service.register(board))
                throw new Exception("Write faild");
            rttr.addFlashAttribute("msg","WRT_OK");
            //rttr.addAttribute("result", board.getBno());
            rttr.addFlashAttribute("result", board.getBno()); // 새롭게 등록된 게시물의 번호를 같이 전달하기 위해
            return "redirect:/board/list";
        }catch(Exception e){
            e.printStackTrace();
            m.addAttribute(board); //등록하려던 내용 보여주기
            m.addAttribute("msg", "WRT_ERR");
            return "board/register";
        }


    }


    @GetMapping("/{bno}")
    public String get(@PathVariable("bno") Integer bno, Model model){
        //해당 번호에 대한 게시물 조회
        log.info("/{bno}");
        model.addAttribute("board", service.get(bno));
        service.increaseViewCnt(bno);
        return "board/getContent";
    }
    @GetMapping("/modify")
    public String modify(@RequestParam("bno") Integer bno, Model model){
        //게시물 수정페이지 보여주기
        log.info("/modify");
        model.addAttribute("board", service.get(bno));
        return "board/modify";
    }

    @PostMapping("/modify")
    public String modify(Board board, RedirectAttributes rttr) {
        log.info("modify : " + board);

        // board를 수정했다면
        if(service.modify(board) ){
            rttr.addFlashAttribute("result" , "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Integer bno, @RequestParam("writer") String writer, RedirectAttributes rttr) throws Exception {
        log.info("remove : " + bno);
        if(service.remove(bno, writer) == 1 ){
            //board를 삭제시 true이면
            rttr.addFlashAttribute("result" , "success");
        }
        return "redirect:/board/list";
    }
}
