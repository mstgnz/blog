package com.mstgnz.blog.services;

import com.mstgnz.blog.dto.BlogDto;
import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.repositories.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogDto save(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        return blogRepository.save(blog);
    }

    public void delete(Long id) {

    }

    public Object getAll(Object o) {
        return null;
    }

    public Page getAll(Pageable pageable) {
        return null;
    }
}
