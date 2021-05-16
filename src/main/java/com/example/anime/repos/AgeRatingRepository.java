package com.example.anime.repos;

import com.example.anime.models.AgeRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeRatingRepository extends JpaRepository<AgeRating, Integer> {
}
