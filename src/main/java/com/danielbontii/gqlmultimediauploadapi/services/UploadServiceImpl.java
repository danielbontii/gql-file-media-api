package com.danielbontii.gqlmultimediauploadapi.services;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.danielbontii.gqlmultimediauploadapi.models.Asset;
import com.danielbontii.gqlmultimediauploadapi.models.AssetResponse;
import com.danielbontii.gqlmultimediauploadapi.services.impl.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {


    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    public AssetResponse generatePreSignedUrl(Asset asset, HttpMethod httpMethod) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 5);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(s3BucketName, asset.getName())
                        .withExpiration(calendar.getTime())
                        .withMethod(httpMethod);

        asset.getMetadata().forEach(metadata ->
                generatePresignedUrlRequest.putCustomRequestHeader(metadata.getKey(), metadata.getValue()));

        URL presignedUrl = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);

        return new AssetResponse(UUID.randomUUID(), presignedUrl.toString());

    }
}
