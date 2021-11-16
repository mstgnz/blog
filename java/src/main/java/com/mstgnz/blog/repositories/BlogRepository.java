package com.mstgnz.blog.repositories;

import com.mstgnz.blog.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findBySlug(String slug);

    List<Blog> findByUserId(long userId);

    List<Blog> findBySlugOrShortTextAndId(long userId);

}
