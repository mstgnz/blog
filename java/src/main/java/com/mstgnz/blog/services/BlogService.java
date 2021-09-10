package com.mstgnz.blog.services;

import com.mstgnz.blog.dto.BlogDto;
import com.mstgnz.blog.dto.CommentDto;
import com.mstgnz.blog.entities.Blog;
import com.mstgnz.blog.entities.Comment;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.exceptions.NotFoundException;
import com.mstgnz.blog.repositories.BlogRepository;
import com.mstgnz.blog.repositories.CommentRepository;
import com.mstgnz.blog.repositories.UserRepository;
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
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final SlugifyService slugifyService;
    private final ModelMapper modelMapper;

    @Transactional
    public BlogDto save(BlogDto blogDto, User user) {
        List<CommentDto> comment = new ArrayList<CommentDto>();
        Blog blog = new Blog();
        blog.setUserId(user.getId());
        blog.setTitle(blogDto.getTitle());
        blog.setSlug(slugifyService.getSlug(blogDto.getTitle()));
        blog.setShortText(blogDto.getShortText());
        blog.setLongText(blogDto.getLongText());
        blog.setStatus(true);
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        Optional<Blog> checkBlog = blogRepository.findBySlug(blog.getSlug());
        if(!checkBlog.isEmpty()){
            blog.setSlug(blog.getSlug() + "-" + new Date().getTime());
        }
        blog = blogRepository.save(blog);
        List<Comment> commentList = commentRepository.findByBlogId(blog.getId());
        for (Comment cItem : commentList){
            comment.add(new CommentDto(cItem, user));
        }
        return new BlogDto(blog, user, comment);
    }

    public List<BlogDto> getAll() {
        List<Blog> blog = blogRepository.findAll();
        List<BlogDto> blogList = new ArrayList<BlogDto>();
        List<CommentDto> comment = new ArrayList<CommentDto>();
        for (Blog item : blog) {
            User user = userRepository.getOne(item.getUserId());
            List<Comment> commentList = commentRepository.findByBlogId(item.getId());
            BlogDto blogDto = new BlogDto();
            blogDto.setId(item.getId());
            blogDto.setTitle(item.getTitle());
            blogDto.setSlug(item.getSlug());
            blogDto.setShortText(item.getShortText());
            blogDto.setLongText(item.getLongText());
            blogDto.setCreateDate(item.getCreateDate());
            blogDto.setUpdateDate(item.getUpdateDate());
            blogDto.setUserId(item.getUserId());
            blogDto.setFullName(user.getFirstname() +" "+ user.getLastname());
            for (Comment cItem : commentList){
                comment.add(new CommentDto(cItem, user));
            }
            blogDto.setComments(comment);
            blogList.add(blogDto);
        }
        return blogList;
    }

    public BlogDto getBlog(String slug){
        Optional<Blog> blog = blogRepository.findBySlug(slug);
        List<CommentDto> comment = new ArrayList<CommentDto>();
        if(blog.isEmpty()){
            throw new NotFoundException();
        }
        User user = userRepository.getOne(blog.get().getUserId());
        List<Comment> commentList = commentRepository.findByBlogId(blog.get().getId());
        for (Comment cItem : commentList){
            comment.add(new CommentDto(cItem, user));
        }
        return new BlogDto(blog.get(), user, comment);
    }

    public void delete(Long blogId, User user) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if(blog.isEmpty()){
            throw new NotFoundException();
        }
        if(blog.get().getUserId() == user.getId()){
            commentRepository.deleteById(blogId);
        }
    }

}
