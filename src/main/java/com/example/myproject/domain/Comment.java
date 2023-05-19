package com.example.myproject.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {

    private Integer cno;
    private Integer bno;
    private Integer pcno;
    private String comment;
    private String commenter;
    private Date c_regdate;
    private Date c_updatedate;
    private Integer cdep;

    private Integer c_ref_order;


    public Comment(Integer pcno, Integer bno, String comment, String commenter, Integer cdep, Integer c_ref_order) {
        this.pcno = pcno;
        this.bno = bno;
        this.comment = comment;
        this.commenter = commenter;
        this.cdep = cdep;
        this.c_ref_order = c_ref_order;
    }

    public Comment(Integer bno, Integer cno, String comment, String commenter){
        this.bno = bno;
        this.pcno = pcno;
        this.comment = comment;
        this.commenter = commenter;
    }

    public Comment(Integer cno, String commenter){
        this.commenter = commenter;
        this.cno = cno;
    }

    public Comment(Integer cno, String comment, String commenter){
        this.commenter = commenter;
        this.cno = cno;
        this.comment = comment;
    }

}
