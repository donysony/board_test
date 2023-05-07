package com.example.myproject.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Board {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Date updateDate;
    private int view_cnt;
}
