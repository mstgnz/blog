package com.mstgnz.blog.dto;

import com.mstgnz.blog.entities.Comment;
import com.mstgnz.blog.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlogDto {
    private Long id;
    private User user;
    private String title;
    private String content;
    private Boolean status;
    private List<Comment> comments;
    private Date createDate;
    private Date updateDate;
}
