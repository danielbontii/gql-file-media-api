CREATE TABLE assets
(
    id        UUID         NOT NULL,
    name      VARCHAR(255) NOT NULL,
    extension VARCHAR(255) NOT NULL,
    CONSTRAINT pk_assets PRIMARY KEY (id)
);