package com.smartmobility.gada_api;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class UtilTest {
    @Test
    void test(){
        String url = "https://gada-bucket.s3.ap-northeast-2.amazonaws.com/acfecb4b-4b73-4db8-9c88-f265430cafa8-KakaoTalk_Photo_2022-10-14-17-10-13.jpeg";
        String[] test = url.split("https://gada-bucket.s3.ap-northeast-2.amazonaws.com/");
        System.out.println(test[1]);
    }
}
