package com.mstgnz.blog.repositories;

import com.mstgnz.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
