package com.adam9e96.BlogStudy.service;

import com.adam9e96.BlogStudy.domain.User;
import com.adam9e96.BlogStudy.dto.AddUserRequest;
import com.adam9e96.BlogStudy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AddUserRequest 객체를 인수로 받는 addUser 메서드를 가지고 있는 UserService 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                // 패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    // 메서드 추가 JWT
    //
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected User"));

    }

}

