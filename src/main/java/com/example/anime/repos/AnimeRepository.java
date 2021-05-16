package com.example.anime.repos;

import com.example.anime.models.Anime;
import com.example.anime.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Integer> {
    public List<Anime> findByStudioStudioId(int id);
}