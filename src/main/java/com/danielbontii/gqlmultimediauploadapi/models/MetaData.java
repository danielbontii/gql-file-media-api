package com.danielbontii.gqlmultimediauploadapi.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "metadatas")
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String key;
    private String value;

}
