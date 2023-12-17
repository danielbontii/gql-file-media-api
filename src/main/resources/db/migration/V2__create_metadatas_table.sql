CREATE TABLE assets_meta_data
(
    asset_id     UUID NOT NULL,
    meta_data_id UUID NOT NULL
);

CREATE TABLE metadatas
(
    id    UUID NOT NULL,
    key   VARCHAR(255),
    value VARCHAR(255),
    CONSTRAINT pk_metadatas PRIMARY KEY (id)
);

ALTER TABLE assets_meta_data
    ADD CONSTRAINT uc_assets_meta_data_metadata UNIQUE (meta_data_id);

ALTER TABLE assets_meta_data
    ADD CONSTRAINT fk_assmetdat_on_asset FOREIGN KEY (asset_id) REFERENCES assets (id);

ALTER TABLE assets_meta_data
    ADD CONSTRAINT fk_assmetdat_on_meta_data FOREIGN KEY (meta_data_id) REFERENCES metadatas (id);