package com.danielbontii.gqlmultimediauploadapi.services.impl;

import com.amazonaws.HttpMethod;
import com.danielbontii.gqlmultimediauploadapi.models.AssetInput;
import com.danielbontii.gqlmultimediauploadapi.models.AssetResponse;

public interface UploadService {

    AssetResponse generatePreSignedUrl(AssetInput assetInput, HttpMethod httpMethod);
}
