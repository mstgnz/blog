package com.mstgnz.blog.api;

import com.mstgnz.blog.dto.BlogDto;
import com.mstgnz.blog.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogDto>> getAll(){
        return ResponseEntity.ok(blogService.getAll());
    }

    @PostMapping
    public ResponseEntity<BlogDto> save(@RequestBody BlogDto blogDto){
        return ResponseEntity.ok(blogService.save(blogDto));
    }
}
