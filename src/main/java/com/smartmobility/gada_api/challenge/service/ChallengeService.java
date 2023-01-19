package com.smartmobility.gada_api.challenge.service;

import com.smartmobility.gada_api.dto.challenge.ChallengeEventForm;
import com.smartmobility.gada_api.dto.challenge.ChallengeEventImage;
import com.smartmobility.gada_api.mapper.ChallengeMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeMapper challengeMapper;
    private final ModelMapper modelMapper;
    private final Log log;

    public List<ChallengeEventForm> getEvent() throws IOException {
        List<ChallengeEventImage> eventList = challengeMapper.getEvent();

        List<ChallengeEventForm> resultList = new ArrayList<>();
        for(ChallengeEventImage event : eventList){
            byte[] imageByteArray;
            try {
                String imageOriginPath = event.getPath() + "/" + event.getName();
                InputStream imageStream = new FileInputStream(imageOriginPath);
                imageByteArray = IOUtils.toByteArray(imageStream);
            }catch (FileNotFoundException | NullPointerException e){
                log.error(e.getMessage());
                imageByteArray = new byte[]{};
            }
            event.setImage(imageByteArray);

            ChallengeEventForm challengeEventForm = modelMapper.map(event, ChallengeEventForm.class);
            resultList.add(challengeEventForm);
        }
        return resultList;
    }
}
