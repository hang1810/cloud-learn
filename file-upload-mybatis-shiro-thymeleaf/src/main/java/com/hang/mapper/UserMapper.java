package com.hang.mapper;


import com.hang.bean.User;

public interface UserMapper {

    User login(String username, String password);

    User findUserByName(String username);
}
