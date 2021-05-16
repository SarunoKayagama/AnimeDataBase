package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int authorId;

    @Column(name = "author_name")
    private String authorName;

    @ManyToMany
    @JoinTable(
            name = "author_anime",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "anime_id")
    )
    private List<Anime> AnimeList;
}
