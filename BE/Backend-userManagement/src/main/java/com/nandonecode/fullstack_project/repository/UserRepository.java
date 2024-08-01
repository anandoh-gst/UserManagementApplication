package com.nandonecode.fullstack_project.repository;

import com.nandonecode.fullstack_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
