package com.example.anime.repos;

import com.example.anime.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository<Characters, Integer> {
}