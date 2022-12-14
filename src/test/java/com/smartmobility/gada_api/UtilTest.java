package com.smartmobility.gada_api;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {
    @Test
    void test(){
        String url = "https://gada-bucket.s3.ap-northeast-2.amazonaws.com/dev/ec9873a2-5479-4d17-8bf2-909f211d8c04-1670821103.286627.jpeg";
        String[] test = url.split("https://gada-bucket.s3.ap-northeast-2.amazonaws.com/");
        System.out.println(test[1]);
    }

    @Test
    void urlImageTest() {
        List<byte[]> list = new ArrayList();

    }
}
