package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "age_rating")
@Data
public class AgeRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "age_rating_id")
    private int ageRatingId;

    @Column(name = "age_rating")
    private String ageRating;
}
