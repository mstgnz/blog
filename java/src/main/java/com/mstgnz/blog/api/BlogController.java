package com.mstgnz.blog.api;

import com.mstgnz.blog.anotations.CurrentUser;
import com.mstgnz.blog.dto.BlogDto;
import com.mstgnz.blog.dto.CommentDto;
import com.mstgnz.blog.entities.User;
import com.mstgnz.blog.services.BlogService;
import com.mstgnz.blog.services.CommentService;
import com.mstgnz.blog.services.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;
    private final CommentService commentService;

    @CrossOrigin
    @GetMapping("/{slug:[a-z-0-9]+}")
    public ResponseEntity<BlogDto> getBlog(@PathVariable String slug){
        return ResponseEntity.ok(blogService.getBlog(slug));
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<BlogDto>> getAll(){
        return ResponseEntity.ok(blogService.getAll());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<BlogDto> save(@Valid @RequestBody BlogDto blogDto, @CurrentUser User user){
        System.out.println(user.toString());
        return ResponseEntity.ok(blogService.save(blogDto, user));
    }

    @CrossOrigin
    @DeleteMapping("/{blogId:[0-9]+}")
    @Transactional
    public ResponseService deleteBlog(@PathVariable long blogId, @CurrentUser User user){
        blogService.delete(blogId, user);
        return new ResponseService(200,true,"blog silindi","delete",null,null);
    }

    @CrossOrigin
    @PostMapping("/{blogId:[0-9]+}/comment")
    public ResponseEntity<CommentDto> saveComment(@Valid @RequestBody CommentDto commentDto, @PathVariable long blogId, @CurrentUser User user){
        return ResponseEntity.ok(commentService.saveComment(commentDto, blogId, user));
    }

    @CrossOrigin
    @DeleteMapping("/{blogId:[0-9]+}/comment/{commentId:[0-9]+}")
    public ResponseService deleteComment(@PathVariable long blogId, @PathVariable long commentId, @CurrentUser User user){
        commentService.deleteComment(blogId, commentId, user);
        return new ResponseService(200,true,"yorum silindi","delete",null,null);
    }
}
