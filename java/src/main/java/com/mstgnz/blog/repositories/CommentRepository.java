package com.mstgnz.blog.repositories;

import com.mstgnz.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogId(long blogId);

    List<Comment> findByBlogIdAndUserId(long blogId, long userId);
    
}
