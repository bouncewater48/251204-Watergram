package com.bounce.watergram.service;

import com.bounce.watergram.common.SHA256HashingEncoder;
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
        String encodedPassword = SHA256HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId, encodedPassword, name, email);

        return count == 1;
    }

    public boolean isDuplicateId(String loginId) {
        int count = userRepository.countByLoginId(loginId);

        if(count == 0) {
            return false;
        } else {
            return true;
        }

    }

}
