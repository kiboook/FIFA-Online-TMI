package com.fifaonline.tmi.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저_저장_불러오기() {
        // given
        String accessId = "테스트 아이디";
        String nickname = "테스트 닉네임";
        int level = 777;

        userRepository.save(User.builder()
                .accessId(accessId)
                .nickname(nickname)
                .level(level).build());

        // when
        List<User> userList = userRepository.findAll();

        // then
        User user = userList.get(0);
        assertThat(user.getAccessId()).isEqualTo(accessId);
        assertThat(user.getNickname()).isEqualTo(nickname);
        assertThat(user.getLevel()).isEqualTo(level);
    }
}