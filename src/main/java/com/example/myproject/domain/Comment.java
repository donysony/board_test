package com.example.myproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class Comment {

    private Integer cno;
    private Integer bno;
    private Integer pcno;
    private String comment;
    private String commenter;
    private Date regdate;
    private Date updateDate;

    public Comment(Integer bno, Integer pcno, String comment, String commenter){
        this.bno = bno;
        this.pcno = pcno;
        this.comment = comment;
        this.commenter = commenter;
    }

    public Comment(Integer cno, String commenter){
        this.commenter = commenter;
        this.cno = cno;
    }

    public Comment(Integer cno, String commenter, String comment){
        this.commenter = commenter;
        this.cno = cno;
        this.comment = comment;
    }
}
