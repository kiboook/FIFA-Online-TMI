package com.fifaonline.tmi.service;

import com.fifaonline.tmi.api.UserApiClient;
import com.fifaonline.tmi.domain.*;
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
    private final PlayerRepository playerRepository;

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

    public void matchTypeSave(MatchTypeMetaDataResponseDto[] matchTypeDtoArray) {
        for (MatchTypeMetaDataResponseDto val : matchTypeDtoArray) {
            matchTypeRepository.save(val.toEntity());
        }
    }

    public void requestDivisionMetaData() {
        divisionSave(userApiClient.requestDivisionMetaData());
    }

    public void divisionSave(DivisionMetaDataResponseDto[] divisionResponseDtoArray) {
        for (DivisionMetaDataResponseDto val : divisionResponseDtoArray) {
            divisionRepository.save(val.toEntity());
        }
    }

    public void requestPlayerMetaData() {
        playerSave(userApiClient.requestPlayerMetaData());
    }

    public void playerSave(PlayerMetaDataResponseDto[] playerMetaDataResponseDtoArray) {
        for (PlayerMetaDataResponseDto val : playerMetaDataResponseDtoArray) {
            playerRepository.save(val.toEntity());
        }
    }

    public UserDivisionResponseDto[] requestUserDivision(String nickname) {
        String accessId = requestUserAccessId(nickname);
        return userApiClient.requestUserDivision(accessId);
    }

    public BuyRecordResponseDto[] requestBuyRecord(String nickname) {
        String accessId = requestUserAccessId(nickname);
        return userApiClient.requestBuyRecord(accessId);
    }

    public void metaDataInit() {
        requestMatchTypeMetaData();
        requestDivisionMetaData();
//        requestPlayerMetaData();
    }
}