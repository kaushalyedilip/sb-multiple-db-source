package com.example.multiple.db.source.usr.repository;

import com.example.multiple.db.source.usr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
