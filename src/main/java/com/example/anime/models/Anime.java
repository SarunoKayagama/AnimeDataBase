package com.example.anime.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "anime_table", schema = "public")
@Data
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anime_id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "start_date")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @ManyToOne
    @JoinColumn(name = "age_rating_id")
    private AgeRating ageRating;

    @ManyToOne
    @JoinColumn(name = "licensor_id")
    private Licensors licensor;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToMany
    @JoinTable(
            name = "author_anime",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Authors> AuthorList;

    @ManyToMany
    @JoinTable(
            name = "character_anime",
            joinColumns = @JoinColumn(name = "anime_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id")
    )
    private List<Characters> CharacterList;

    public Anime() {
    }

    public Licensors getLicensor() {
        if (this.licensor == null) {
            Licensors licensors = new Licensors();
            licensors.setLicensor("Нет");
            return licensors;

        }
        return licensor;
    }
    public String toString(){
        return "";
    }
}
