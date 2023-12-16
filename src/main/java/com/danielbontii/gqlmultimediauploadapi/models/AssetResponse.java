package com.danielbontii.gqlmultimediauploadapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetResponse {
    private UUID id;
    private String presignedUrl;
}
