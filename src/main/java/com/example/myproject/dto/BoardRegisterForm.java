package com.example.myproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRegisterForm {
    private String title;
    private String content;
    private String writer;
}
