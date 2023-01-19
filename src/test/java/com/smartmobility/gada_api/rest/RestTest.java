package com.smartmobility.gada_api.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

@SpringBootTest
public class RestTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    void test() throws IOException {
        URL url = new URL("https://gada-bucket.s3.ap-northeast-2.amazonaws.com/61f14837-d83b-4afc-b6b3-6dad86d5f60d-02_%ED%8A%B9%EC%A7%84%EC%B4%88%EB%B0%A5%201.png");
    }
}
