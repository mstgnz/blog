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
public class BlogDto implements IDto{
    private Long id;
    private Long userId;
    @NotEmpty
    @NotNull
    private String title;
    private String slug;
    @NotEmpty
    @NotNull
    private String shortText;
    @NotEmpty
    @NotNull
    private String longText;
    private Boolean status;
    private List<CommentDto> comments;
    private String fullName;
    private Date createDate;
    private Date updateDate;

    public BlogDto(){

    }

    public BlogDto(Blog blog, User user, List<CommentDto> commentDto){
        this.id = blog.getId();
        this.fullName = user.getFirstname() + user.getLastname();
        this.title = blog.getTitle();
        this.slug = blog.getSlug();
        this.shortText = blog.getShortText();
        this.longText = blog.getLongText();
        this.status = blog.getStatus();
        this.createDate = blog.getCreateDate();
        this.updateDate = blog.getUpdateDate();
        this.comments = commentDto;
    }
}
