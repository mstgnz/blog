package com.mstgnz.blog.services;

import com.mstgnz.blog.dto.BlogDto;
import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.repositories.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BlogDto save(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog = blogRepository.save(blog);
        blogDto.setTitle(blog.getTitle());
        blogDto.setContent(blog.getContent());
        return blogDto;
    }

    public List<BlogDto> getAll() {
        List<Blog> blog = blogRepository.findAll();
        List<BlogDto> blogList = new ArrayList<BlogDto>();
        blog.forEach(item->{
            BlogDto blogDto = new BlogDto();
            blogDto.setId(item.getId());
            blogDto.setTitle(item.getTitle());
            blogDto.setContent(item.getContent());
            blogDto.setCreateDate(item.getCreateDate());
            blogDto.setUpdateDate(item.getUpdateDate());
            blogDto.setUser(item.getUser());
            blogList.add(blogDto);
        });
        return blogList;
    }

    public void delete(Long id) {

    }

}
