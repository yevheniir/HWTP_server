package com.yevhenii.to_do_list.service;

import com.yevhenii.to_do_list.bean.PasswordEncoder;
import com.yevhenii.to_do_list.model.User;
import com.yevhenii.to_do_list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}