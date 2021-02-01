package com.fifaonline.tmi.service;

import com.fifaonline.tmi.api.UserApiClient;
import com.fifaonline.tmi.domain.User;
import com.fifaonline.tmi.domain.UserRepository;
import com.fifaonline.tmi.web.dto.UserApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserApiClient userApiClient;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserApiResponseDto requestUserInfo(String nickname) {
        return userApiClient.requestUserInfo(nickname);
    }

    @Transactional
    public Long save(UserApiResponseDto userApiResponseDto) {
        return userRepository.save(userApiResponseDto.toEntity()).getId();
    }

    public UserApiResponseDto findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("구단주가 존재하지 않습니다!"));

        return
    }
}
