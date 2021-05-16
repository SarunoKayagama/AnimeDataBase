package com.example.anime.repos;

import com.example.anime.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Integer> {
}
