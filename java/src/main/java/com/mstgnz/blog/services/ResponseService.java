package com.mstgnz.blog.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mstgnz.blog.dto.IDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseService implements IService{
    private int status = 200;
    private boolean success = true;
    private String message = "";
    private String lang = "";
    private IDto data = null;
    private Map<String, String> errors = null;

}