package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "type")
@Data
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type")
    private String type;
}
