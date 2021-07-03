package com.example.multiple.db.source.usr.service.impl;

import com.example.multiple.db.source.usr.entity.User;
import com.example.multiple.db.source.usr.repository.UserRepository;
import com.example.multiple.db.source.usr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
