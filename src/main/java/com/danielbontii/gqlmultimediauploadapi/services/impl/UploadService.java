package com.danielbontii.gqlmultimediauploadapi.services.impl;

import com.amazonaws.HttpMethod;
import com.danielbontii.gqlmultimediauploadapi.models.Asset;
import com.danielbontii.gqlmultimediauploadapi.models.AssetResponse;

public interface UploadService {

    AssetResponse generatePreSignedUrl(Asset asset, HttpMethod httpMethod);
}
