package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
@Data
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private int characterId;

    @Column(name = "character_name")
    private String characterName;

    @ManyToMany
    @JoinTable(
            name = "character_anime",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "anime_id")
    )
    private List<Anime> AnimeList;
}
