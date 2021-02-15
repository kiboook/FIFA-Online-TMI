package com.fifaonline.tmi.service;

import com.fifaonline.tmi.api.UserApiClient;
import com.fifaonline.tmi.domain.DivisionRepository;
import com.fifaonline.tmi.domain.MatchTypeRepository;
import com.fifaonline.tmi.domain.User;
import com.fifaonline.tmi.domain.UserRepository;
import com.fifaonline.tmi.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserApiClient userApiClient;
    private final UserRepository userRepository;
    private final MatchTypeRepository matchTypeRepository;
    private final DivisionRepository divisionRepository;

    @Transactional(readOnly = true)
    public UserApiResponseDto requestUserInfo(String nickname) {
        return userApiClient.requestUserInfo(nickname);
    }

    @Transactional
    public String userInfoSave(UserApiResponseDto userApiResponseDto) {
        return userRepository.save(userApiResponseDto.toEntity()).getNickname();
    }

    public UserInfoResponseDto userInfoFindById(String nickname) {
        User entity = userRepository.findById(nickname).orElseThrow(() -> new IllegalArgumentException("구단주가 존재하지 않습니다!"));

        return new UserInfoResponseDto(entity);
    }

    public String requestUserAccessId(String nickname) {
        User entity = userRepository.findById(nickname).orElseThrow(() -> new IllegalArgumentException("구단주가 존재하지 않습니다!"));

        return entity.getAccessId();
    }

    public void requestMatchTypeMetaData() {
        matchTypeSave(userApiClient.requestMatchTypeMetaData());
    }

    public void matchTypeSave(MatchTypeResponseDto[] matchTypeDtoArray) {
        for (MatchTypeResponseDto val : matchTypeDtoArray) {
            matchTypeRepository.save(val.toEntity());
        }
    }

    public void requestDivisionMetaData() {
        divisionSave(userApiClient.requestDivisionMetaData());
    }

    public void divisionSave(DivisionResponseDto[] divisionResponseDtoArray) {
        for (DivisionResponseDto val : divisionResponseDtoArray) {
            divisionRepository.save(val.toEntity());
        }
    }

    public UserDivisionResponseDto[] requestUserDivision(String nickname) {
        String accessId = requestUserAccessId(nickname);
        return userApiClient.requestUserDivision(accessId);
    }

    public void metaDataInit() {
        requestMatchTypeMetaData();
        requestDivisionMetaData();
    }
}