package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping("/")
    public String index(Model model){
//        model.addAttribute("album", albumRepository.findAll());
//        return "index";

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
    @GetMapping("/add")
    public String albumForm(Model model){
        model.addAttribute("album", new Album());
        return "albumform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Album album,
                              BindingResult result){
        if (result.hasErrors()){
            return "albumform";
        }
        albumRepository.save(album);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("album", albumRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateAlbum(@PathVariable("id") long id,
                              Model model){
        model.addAttribute("album", albumRepository.findById(id).get());
        return "albumform";
    }

    @RequestMapping("/delete/{id}")
    public String delAlbum(@PathVariable("id") long id){
        albumRepository.deleteById(id);
        return "redirect:/";
    }

}
