package com.hang.service;


import com.hang.bean.User;

public interface UserService {

    User login(String username, String password);

    User findUserByName(String username);
}
