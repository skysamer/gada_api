package com.smartmobility.gada_api.dto.challenge;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeEventImage {
    private Long id;
    private String name;
    private String path;
    private Long event_id;

    private String title;

    private String content;

    private LocalDate started_at;

    private LocalDate ended_at;

    private byte[] image;

    public void setImage(byte[] image){
        this.image = image;
    }
}
