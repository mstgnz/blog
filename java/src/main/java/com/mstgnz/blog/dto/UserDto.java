package com.mstgnz.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mstgnz.blog.anotations.UniqueEmail;
import com.mstgnz.blog.entities.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements IDto {
    private long id;
    @NotEmpty
    @NotNull
    @UniqueEmail
    private String email;
    @NotEmpty
    @NotNull
    private String password;
    @NotEmpty
    @NotNull
    private String firstname;
    @NotEmpty
    @NotNull
    private String lastname;
    private Date createDate;
    private Date updateDate;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }
}