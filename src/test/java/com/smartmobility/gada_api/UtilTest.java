package com.smartmobility.gada_api;

import com.amazonaws.util.IOUtils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

public class UtilTest {
    @Test
    void test(){
        String url = "https://gada-bucket.s3.ap-northeast-2.amazonaws.com/dev/ec9873a2-5479-4d17-8bf2-909f211d8c04-1670821103.286627.jpeg";
        String[] test = url.split("https://gada-bucket.s3.ap-northeast-2.amazonaws.com/");
        System.out.println(test[1]);
    }

    @Test
    void urlImageTest() {
        try {
            String IMAGE_URL = "https://gada-bucket.s3.ap-northeast-2.amazonaws.com/dev/ec9873a2-5479-4d17-8bf2-909f211d8c04-1670821103.286627.jpeg";
            URL url = new URL(IMAGE_URL);
            String extension = IMAGE_URL.substring(IMAGE_URL.lastIndexOf('.') + 1);
            System.out.println(extension);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
