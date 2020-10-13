package com.hang.service;


import com.hang.bean.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    User login(String username, String password);

    User findUserByName(String username);
}
