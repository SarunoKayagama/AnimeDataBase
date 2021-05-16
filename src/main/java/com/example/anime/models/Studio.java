package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "studio")
@Data
public class Studio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private int studioId;

    @Column(name = "studio")
    private String studio;
}
