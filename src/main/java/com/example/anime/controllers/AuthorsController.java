package com.example.anime.controllers;
import com.example.anime.models.Anime;
import com.example.anime.models.Authors;
import com.example.anime.models.Characters;
import com.example.anime.repos.AnimeRepository;
import com.example.anime.repos.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthorsController {
    private AuthorsRepository authorsRepository;
    private  AnimeRepository animeRepository;

    @Autowired
    public AuthorsController(AuthorsRepository authorsRepository, AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
        this.authorsRepository = authorsRepository;
    }

    @GetMapping(value = {"/Authors", "/Авторы"})
    public String Char(Model model) {
        List<Authors> all = authorsRepository.findAll();
        model.addAttribute("AuthorsList", all);
        return "Authors";
    }

    @GetMapping({"Authors/{id}", "Anime/Authors/{id}", "Author/{id}", "Anime/Author/{id}"})
    public String show(@PathVariable("id") int author_id, Model model){
        Optional<Authors> byId = authorsRepository.findById(author_id);
        if (byId.isPresent()){
            model.addAttribute( "author", byId.get() );
        }
        else {
            return "NotFound";
        }
        return "AuthorInfo";
    }
    @GetMapping({"/Authors/Edit/{id}","/Authors/Edit/{id}"})
    public String edit(@PathVariable("id") int author_id, Model model){
        Optional<Authors> byId = authorsRepository.findById(author_id);
        if (byId.isPresent()){
            model.addAttribute( "author", byId.get() );
            model.addAttribute("animeAllList", animeRepository.findAll());
        }
        else {
            return "NotFound";
        }
        return "AuthorEdit";
    }
    @PatchMapping("/Authors/{id}")
    public String update(@PathVariable("id") int id, Model model, @ModelAttribute() Authors author){
        authorsRepository.save(author);
        return "redirect:/Authors";
    }
    @GetMapping({"/Authors/New","Author/New"})
    public String newAut(@ModelAttribute("author") Authors authors, Model model) {
        model.addAttribute("animeAllList",animeRepository.findAll());
        return "NewAut";
    }

    @PostMapping("/Authors/New")
    public String create(@ModelAttribute("authors") Authors authors){
        authorsRepository.save(authors);
        return "redirect:/Authors";
    }

    @GetMapping({"/Authors/Delete/{id}","/Authors/{id}/Delete","/Author/Delete/{id}","/Author/{id}/Delete"})
    public String delete(@PathVariable("id") int aut_id, Model model) {
        Optional<Authors> byId = authorsRepository.findById(aut_id);
        if (byId.isPresent()) {
            authorsRepository.deleteById(aut_id);
        } else {
            return "NotFound";
        }
        return "redirect:/Authors";
    }

    @GetMapping({"/Authors/Find/{partName}"})
    public  String findAn(@PathVariable("partName") String name, Model model){
        List<Authors> all = authorsRepository.findAll();
        List<Authors> authorsFinded = new ArrayList<Authors>();
        name = name.toLowerCase();
        System.out.println(name);
        for (Authors authors : all
        ) {if (authors.getAuthorName().toLowerCase().contains(name)){
            authorsFinded.add(authors);
        }

        }
        model.addAttribute("AuthorsList", authorsFinded);
        if (authorsFinded.size() == 0) {return "NotFound";}
        return "Authors";
    }

}