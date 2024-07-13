package com.example.song.repository;

import com.example.song.model.Song;
import java.util.*;

public interface SongRepository {
    public ArrayList<Song> getSongs();

    public Song getSongById(int id);

    public Song addSong(Song song);

    public Song updateSong(int id, Song song);

    public void deleteSong(int id);
}