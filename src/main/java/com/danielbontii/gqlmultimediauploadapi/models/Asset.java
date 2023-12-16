package com.danielbontii.gqlmultimediauploadapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    private UUID id;
    private String name;
    private String extension;
    private List<MetaData> metadata;
}
