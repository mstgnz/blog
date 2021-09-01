package com.mstgnz.blog.dto;

import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.entities.Comment;
import com.mstgnz.blog.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BlogDto implements IDto{
    private Long id;
    private User user;
    private String title;
    private String slug;
    private String shortText;
    private String longText;
    private Boolean status;
    private List<Comment> comments;
    private Date createDate;
    private Date updateDate;

    public BlogDto(){

    }

    public BlogDto(Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.slug = blog.getSlug();
        this.shortText = blog.getShortText();
        this.longText = blog.getLongText();
        this.createDate = blog.getCreateDate();
        this.updateDate = blog.getUpdateDate();
    }
}