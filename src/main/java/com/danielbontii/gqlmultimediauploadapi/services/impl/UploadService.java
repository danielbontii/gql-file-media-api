package com.danielbontii.gqlmultimediauploadapi.services.impl;

import com.amazonaws.HttpMethod;
import com.danielbontii.gqlmultimediauploadapi.models.AssetInput;
import com.danielbontii.gqlmultimediauploadapi.models.AssetResponse;

import java.util.List;

public interface UploadService {

    List<AssetResponse> generatePreSignedUrl(List<AssetInput> assetInputs, HttpMethod httpMethod);
}
