package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "licensors")
@Data
public class Licensors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "licensor_id")
    private int licensorId;

    @Column(name = "licensor")
    private String licensor;

    public Licensors() {
    }
}
