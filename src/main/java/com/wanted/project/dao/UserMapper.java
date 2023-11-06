package com.wanted.project.dao;

import com.wanted.project.core.Mapper;
import com.wanted.project.model.User;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<User> findAll();

    User findUserByName(String name);

    int createUser(User userPO);
}
