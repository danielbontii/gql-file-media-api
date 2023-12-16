package com.danielbontii.gqlmultimediauploadapi.repositories;

import com.danielbontii.gqlmultimediauploadapi.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
}