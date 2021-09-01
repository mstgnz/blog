package com.mstgnz.blog.services;

import com.mstgnz.blog.dto.CommentDto;
import com.mstgnz.blog.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements IService {

    private final CommentRepository commentRepository;

    public CommentDto create(CommentDto commentDto){
        //commentRepository.save(commentDto);
        return commentDto;
    }

}
