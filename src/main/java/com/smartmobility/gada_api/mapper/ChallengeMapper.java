package com.smartmobility.gada_api.mapper;

import com.smartmobility.gada_api.dto.challenge.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengeMapper {
    List<ChallengeEventImage> getEvent();

    List<ChallengeStoreForm> getStoreList();
    ChallengeStoreForm getStore(Long id);
    ChallengeStoreImage getStoreImage(Long id);

    void plusLikes(Long id);
    void minusLikes(Long id);
}
