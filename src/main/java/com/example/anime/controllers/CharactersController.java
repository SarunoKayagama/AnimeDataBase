package com.example.anime.controllers;

import com.example.anime.models.Anime;
import com.example.anime.models.Authors;
import com.example.anime.models.Characters;
import com.example.anime.repos.AnimeRepository;
import com.example.anime.repos.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CharactersController {
    private CharactersRepository charactersRepository;
    private AnimeRepository animeRepository;

    @Autowired
    public CharactersController(CharactersRepository charactersRepository, AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
        this.charactersRepository = charactersRepository;
    }

    @GetMapping(value = {"/Characters", "/Персонажи", "/Character"})
    public String Char(Model model) {
        List<Characters> all = charactersRepository.findAll();
        model.addAttribute("CharacterList", all);
        return "Characters";
    }

    @GetMapping({"/Characters/{id}","/Character/{id}"})
    public String show(@PathVariable("id") int character_id, Model model){
        Optional<Characters> byId = charactersRepository.findById(character_id);
        if (byId.isPresent()){
            model.addAttribute( "character", byId.get() );
        }
        else {
            return "NotFound";
        }
        return "CharacterInfo";
    }
    @GetMapping({"/Characters/Edit/{id}","/Character/Edit/{id}"})
    public String edit(@PathVariable("id") int character_id, Model model){
        Optional<Characters> byId = charactersRepository.findById(character_id);
        if (byId.isPresent()){
            model.addAttribute( "character", byId.get() );
            model.addAttribute("animeAllList", animeRepository.findAll());
        }
        else {
            return "NotFound";
        }
        return "CharacterEdit";
    }

    @PostMapping("/Characters/New")
    public String create(@ModelAttribute("authors") Characters characters){
        charactersRepository.save(characters);
        return "redirect:/Characters";
    }

    @PatchMapping({"/Characters/{id}","/Character/{id}"})
    public String update(@PathVariable("id") int id, Model model, @ModelAttribute() Characters characters){
        charactersRepository.save(characters);
        return "redirect:/Characters";
    }
    @GetMapping({"/Characters/New","Character/New"})
    public String newChar(@ModelAttribute("character") Characters characters, Model model) {
        model.addAttribute("animeAllList",animeRepository.findAll());
        return "NewChar";
    }
    @GetMapping({"/Characters/Delete/{id}","/Characters/{id}/Delete","/Character/Delete/{id}","/Character/{id}/Delete"})
    public String delete(@PathVariable("id") int char_id, Model model) {
        Optional<Characters> byId = charactersRepository.findById(char_id);
        if (byId.isPresent()) {
            charactersRepository.deleteById(char_id);
        } else {
            return "NotFound";
        }
        return "redirect:/Characters";
    }

    @GetMapping({"/Characters/Find/{partName}"})
    public  String findAn(@PathVariable("partName") String name, Model model){
        List<Characters> all = charactersRepository.findAll();
        List<Characters> charactersFinded = new ArrayList<Characters>();
        name = name.toLowerCase();
        System.out.println(name);
        for (Characters anime : all
        ) {if (anime.getCharacterName().toLowerCase().contains(name)){
            charactersFinded.add(anime);
        }

        }
        System.out.println(charactersFinded);
        model.addAttribute("CharacterList", charactersFinded);
        if (charactersFinded.size() == 0) {return "NotFound";}
        return "Characters";
    }
}