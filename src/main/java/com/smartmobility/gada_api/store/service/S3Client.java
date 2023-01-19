package com.smartmobility.gada_api.store.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Client {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${cloud.aws.url}")
    private String awsS3Url;
    @Value("${cloud.aws.s3.dir}")
    private String dir;
    @Value("${is-dev}")
    private String isDev;
    private final AmazonS3 amazonS3;
    private final Log log = LogFactory.getLog(getClass());

    /*s3에 이미지 업로드*/
    public String upload(MultipartFile image) throws IOException {
        String s3FileName = makeS3Filename(image.getOriginalFilename(), isDev);
        sendImageObjectToS3(image, s3FileName);

        log.info("image upload complete");
        return amazonS3.getUrl(bucket, s3FileName).toString();
    }

    /*파일을 구분하기 위한 고유이미지이름 생성 (개발용일 경우 별도 디렉토리에 저장하도록 구분)*/
    private String makeS3Filename(String originalImageName, String isDev){
        if(isDev.equals("yes")){
            return dir +  "/" + UUID.randomUUID() + "-" + originalImageName;
        }
        return UUID.randomUUID() + "-" + originalImageName;
    }

    /*파일을 객체변환하여 s3에 전송하는 로직*/
    private void sendImageObjectToS3(MultipartFile image, String s3FileName) throws IOException {
        ObjectMetadata s3Object = new ObjectMetadata();
        s3Object.setContentLength(image.getInputStream().available());
        s3Object.setContentType(image.getContentType());
        amazonS3.putObject(bucket, s3FileName, image.getInputStream(), s3Object);
    }

    /*업로드된 이미지 삭제*/
    public void remove(String imageUrl){
        String[] imageInfo = imageUrl.split(awsS3Url);
        amazonS3.deleteObject(bucket, imageInfo[1]);
        log.info("image delete complete");
    }
}
