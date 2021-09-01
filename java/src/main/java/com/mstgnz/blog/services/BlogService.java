package com.mstgnz.blog.services;

import com.github.slugify.Slugify;
import com.mstgnz.blog.dto.BlogDto;
import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.repositories.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService implements IService{

    private final BlogRepository blogRepository;
    private final SlugifyService slugifyService;
    private final ModelMapper modelMapper;

    @Transactional
    public BlogDto save(BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setSlug(slugifyService.getSlug(blogDto.getTitle()));
        blog.setShortText(blogDto.getShortText());
        blog.setLongText(blogDto.getLongText());
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        Optional<Blog> checkBlog = blogRepository.findBySlug(blog.getSlug());
        if(!checkBlog.isEmpty()){
            blog.setSlug(blog.getSlug() + "-" + new Date().getTime());
        }
        blog = blogRepository.save(blog);
        blogDto.setId(blog.getId());
        blogDto.setTitle(blog.getTitle());
        blogDto.setSlug(blog.getSlug());
        blogDto.setShortText(blog.getShortText());
        blogDto.setLongText(blog.getLongText());
        blogDto.setCreateDate(blog.getCreateDate());
        blogDto.setUpdateDate(blog.getUpdateDate());
        return blogDto;
    }

    public List<BlogDto> getAll() {
        List<Blog> blog = blogRepository.findAll();
        List<BlogDto> blogList = new ArrayList<BlogDto>();
        for (Blog item : blog) {
            BlogDto blogDto = new BlogDto();
            blogDto.setId(item.getId());
            blogDto.setTitle(item.getTitle());
            blogDto.setShortText(item.getShortText());
            blogDto.setLongText(item.getLongText());
            blogDto.setCreateDate(item.getCreateDate());
            blogDto.setUpdateDate(item.getUpdateDate());
            blogDto.setUser(item.getUser());
            blogList.add(blogDto);
        }
        return blogList;
    }

    public void delete(Long id) {

    }

}
