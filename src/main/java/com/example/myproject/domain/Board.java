package com.example.myproject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class Board {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updateDate;
    private int view_cnt;

    public Board(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    public Board(Integer bno, String writer){
        this.bno = bno;
        this.writer = writer;
    }
}
