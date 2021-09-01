package com.mstgnz.blog.dto;

import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.entities.Comment;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDto implements IDto{
    private Long id;
    private Blog blog;
    private String comment;
    private Date createDate;

    public CommentDto(){

    }

    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createDate = comment.getCreateDate();
    }
}
