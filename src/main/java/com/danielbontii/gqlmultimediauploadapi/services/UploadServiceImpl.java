package com.danielbontii.gqlmultimediauploadapi.services;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.danielbontii.gqlmultimediauploadapi.models.Asset;
import com.danielbontii.gqlmultimediauploadapi.models.AssetInput;
import com.danielbontii.gqlmultimediauploadapi.models.AssetResponse;
import com.danielbontii.gqlmultimediauploadapi.repositories.AssetRepository;
import com.danielbontii.gqlmultimediauploadapi.services.impl.UploadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {


    private final AmazonS3 amazonS3;
    private final ObjectMapper objectMapper;
    private final AssetRepository assetRepository;

    @Value("${aws.s3.bucketName}")
    private String s3BucketName;

    @Transactional
    public List<AssetResponse> generatePreSignedUrl(List<AssetInput> assetInputs, HttpMethod httpMethod) {

        //TODO: Publish this into a queue

        List<AssetResponse> responses = new ArrayList<>();

        assetInputs.forEach(assetInput -> {


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE, 5);

            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(s3BucketName, assetInput.getName())
                            .withExpiration(calendar.getTime())
                            .withMethod(httpMethod);

            //Todo: Investigate headers changing presignedUrlRequest Signature
//        assetInput.getMetadata().forEach(data ->
//                generatePresignedUrlRequest.putCustomRequestHeader("x-amz-meta-"+data.getKey(), data.getValue()));

            URL presignedUrl = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);


            Asset assetToSave = new Asset();
            assetToSave.setName(assetInput.getName());
            assetToSave.setExtension(assetInput.getExtension());
            assetToSave.setMetaData(assetInput.getMetadata());


            Asset savedAsset = assetRepository.save(assetToSave);

            AssetResponse response = objectMapper.convertValue(savedAsset, AssetResponse.class);
            response.setPresignedUrl(presignedUrl.toString());

            responses.add(response);

        });

        return responses;

    }
}
