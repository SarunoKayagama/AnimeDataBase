package com.example.anime.repos;

import com.example.anime.models.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
}