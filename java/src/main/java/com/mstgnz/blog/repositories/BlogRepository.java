package com.mstgnz.blog.repositories;

import com.mstgnz.blog.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}
