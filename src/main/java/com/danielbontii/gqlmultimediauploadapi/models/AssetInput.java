package com.danielbontii.gqlmultimediauploadapi.models;

import lombok.Data;

import java.util.List;

@Data
public class AssetInput {

    private String name;
    private String extension;
    private List<MetaData> metadata;
}
