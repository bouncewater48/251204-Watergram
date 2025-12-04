package com.bounce.watergram.service;

import com.bounce.watergram.common.MD5HashingEncoder;
import com.bounce.watergram.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email) {

//        비밀번호 해싱
        String encodedPassword = MD5HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId, encodedPassword, name, email);

        if(count == 1) {
            return true;
        } else {
            return false;
        }

    }

}
