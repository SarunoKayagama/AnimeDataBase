package com.example.anime.controllers;

import com.example.anime.models.Anime;
import com.example.anime.models.Studio;
import com.example.anime.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class AnimeController {
    private AnimeRepository animeRepository;
    private AuthorsRepository authorsRepository;
    private CharactersRepository charactersRepository;
    private LicensorsRepository licensorsRepository;
    private StudioRepository studioRepository;
    private StatusRepository statusRepository;
    private AgeRatingRepository ageRatingRepository;

    @Autowired
    public AnimeController(AnimeRepository animeRepository, AuthorsRepository authorsRepository, CharactersRepository charactersRepository, LicensorsRepository licensorsRepository, StudioRepository studioRepository, StatusRepository statusRepository, AgeRatingRepository ageRatingRepository) {
        this.charactersRepository = charactersRepository;
        this.statusRepository = statusRepository;
        this.ageRatingRepository = ageRatingRepository;
        this.authorsRepository = authorsRepository;
        this.licensorsRepository = licensorsRepository;
        this.studioRepository = studioRepository;
        this.animeRepository = animeRepository;
    }

    @GetMapping(value = {"/Anime", "/"})
    public String home(Model model) {
        System.out.println("123");
        List<Anime> all = animeRepository.findAll();
        System.out.println(all.size());
        model.addAttribute("animeList",all);
        return "Anime";
    }

    @GetMapping("/Anime/{id}")
    public String show(@PathVariable("id") int anime_id, Model model){
        Optional<Anime> byId = animeRepository.findById(anime_id);
        if (byId.isPresent()){
            model.addAttribute( "anime", byId.get() );
        }
        else {
            return "NotFound";
        }
        return "AnimeInfo";
    }

    @GetMapping("/Anime/Edit/{id}")
    public String edit(@PathVariable("id") int anime_id, Model model){
        Optional<Anime> byId = animeRepository.findById(anime_id);
        if (byId.isPresent()){
            model.addAttribute( "anime", byId.get() );
            model.addAttribute("authors", authorsRepository.findAll());
            model.addAttribute("characters", charactersRepository.findAll());
            model.addAttribute("licensors", licensorsRepository.findAll());
            model.addAttribute("studios", studioRepository.findAll());
        }
        else {
            return "NotFound";
        }
        return "AnimeEdit";
    }



    @PostMapping("/Anime/New")
    public String create(@ModelAttribute("anime") Anime anime){
        animeRepository.save(anime);
        return "redirect:/Anime";
    }

    @PatchMapping("/Anime/{id}")
    public String update(@PathVariable("id") int id, Model model, @ModelAttribute() Anime anime){
        animeRepository.save(anime);
        return "redirect:/Anime";
    }

    @GetMapping({"/Anime/Delete/{id}","/Anime/{id}/Delete"})
    public String delete(@PathVariable("id") int anime_id, Model model){
        Optional<Anime> byId = animeRepository.findById(anime_id);
        if (byId.isPresent()){
            animeRepository.deleteById(anime_id);
        }
        else {
            return "NotFound";
        }

        return "redirect:/Anime";
    }

    @GetMapping({"/Anime/New","New"})
    public String newAnime(@ModelAttribute("anime") Anime anime, Model model){
        model.addAttribute("authors", authorsRepository.findAll());
        model.addAttribute("characters", charactersRepository.findAll());
        model.addAttribute("licensors", licensorsRepository.findAll());
        model.addAttribute("studios", studioRepository.findAll());
        return "New";
    }

    @GetMapping({"/Anime/Find/{partName}"})
    public  String findAn(@PathVariable("partName") String name, Model model){
        List<Anime> all = animeRepository.findAll();
        List<Anime> animeFinded = new ArrayList<Anime>();
        name = name.toLowerCase();
        System.out.println(name);
        for (Anime anime : all
             ) {if (anime.getName().toLowerCase().contains(name)){
                 animeFinded.add(anime);
        }
            
        }
        model.addAttribute("animeList", animeFinded);
        if (animeFinded.size() == 0) {return "NotFound";}
        return "Anime";
    }

    @PostMapping("/Anime")
    public String postFind(String name){
        return "redirect: Anime/Find/{name}";
    }

    @GetMapping({"/Anime/Find/Studio/{id}"})
    public  String findSt(@PathVariable("id") int id, Model model){
        List<Anime> byStudioStudioId = animeRepository.findByStudioStudioId(id);
        model.addAttribute("animeList", byStudioStudioId);
        return  "Anime";
    }
}
