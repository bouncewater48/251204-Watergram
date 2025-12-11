package com.bounce.watergram.user.service;

import com.bounce.watergram.common.SHA256HashingEncoder;
import com.bounce.watergram.user.repository.UserRepository;
import com.bounce.watergram.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

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

        if(count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDuplicateId(String loginId) {
        int count = userRepository.countByLoginId(loginId);

        if(count == 0) {
            return false;
        } else {
            return true;
        }

    }

    public User getUser(String loginId, String password) {

        String encodedPassword = SHA256HashingEncoder.encode(password);
            // encodedPassword로 해싱된 비밀번호를 그대로 사용하여 로그인에 사용
        User user = userRepository.selectUser(loginId, encodedPassword);

        return user;

    }



}
