package com.mstgnz.blog.services;

import com.mstgnz.blog.dto.CommentDto;
import com.mstgnz.blog.entities.Comment;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.exceptions.NotFoundException;
import com.mstgnz.blog.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentService implements IService {

    private final CommentRepository commentRepository;

    public CommentDto saveComment(CommentDto commentDto, long blogId, User user) {
        Comment comment = new Comment();
        comment.setBlogId(blogId);
        comment.setUserId(user.getId());
        comment.setComment(commentDto.getComment());
        comment.setCreateDate(new Date());
        comment = commentRepository.save(comment);
        return new CommentDto(comment, user);
    }

    public void deleteComment(long blogId, long commentId, User user) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new NotFoundException();
        }
        if(comment.get().getUserId() == user.getId() && comment.get().getBlogId() == blogId){
            commentRepository.deleteById(commentId);
        }
    }
}
