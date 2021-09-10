package com.mstgnz.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.entities.Comment;
import com.mstgnz.blog.entities.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDto implements IDto{
    private Long id;
    private Long userId;
    private Long blogId;
    private String fullName;
    @NotEmpty
    @NotNull
    private String comment;
    private Date createDate;

    public CommentDto(){

    }

    public CommentDto(Comment comment, User user){
        this.id = comment.getId();
        this.fullName = user.getFirstname() + user.getLastname();
        this.comment = comment.getComment();
        this.createDate = comment.getCreateDate();
    }

}
