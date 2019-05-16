package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping("/")
    public String index(Model model){
        Album album = new Album();
        album.setName("Help");
        album.setYear("1662");

        Song song = new Song();
        song.setName("Yesterday");
        song.setComposer("Paul McCartney");

        Set<Song>songs = new HashSet<>();
        songs.add(song);

        song = new Song();
        song.setName("Hey Jude");
        song.setComposer("Paul McCartney");
        songs.add(song);

        album.setSongs(songs);

        albumRepository.save(album);

        model.addAttribute("albums", albumRepository.findAll());
        return "index";

    }

}
