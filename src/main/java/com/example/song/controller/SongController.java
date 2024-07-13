package com.example.song.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.song.service.SongH2Service;
import com.example.song.model.Song;

@RestController
public class SongController {

    @Autowired
    private SongH2Service songService;

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int Id) {
        return songService.getSongById(Id);
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int Id, @RequestBody Song song) {
        return songService.updateSong(Id, song);
    }

    @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable("songId") int Id) {
        songService.deleteSong(Id);
    }

}
