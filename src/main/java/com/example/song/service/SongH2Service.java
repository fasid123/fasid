package com.example.song.service;

import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;
import com.example.song.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service

public class SongH2Service implements SongRepository {

    @Autowired

    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getSongs() {
        List<Song> listo = db.query("SELECT * FROM playlist", new SongRowMapper());
        ArrayList<Song> arr1 = new ArrayList<>(listo);
        return arr1;
    }

    @Override
    public Song getSongById(int id) {

        try

        {
            return db.queryForObject("select * from playlist where songId = ?", new SongRowMapper(), id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Song addSong(Song song) {
        db.update("insert into playlist(songName,lyricist,singer,musicDirector) values (?,?,?,?)", song.getSongName(),
                song.getLyricist(), song.getSinger(), song.getMusicDirector());
        return db.queryForObject(
                "select * from playlist where songName = ? and lyricist = ? and singer = ? and musicDirector = ?",
                new SongRowMapper(),
                song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
    }

    @Override
    public void deleteSong(int id) {
        db.update("delete from playlist where songId = ?", id);
    }

    @Override
    public Song updateSong(int id, Song song) {
        if (song.getSongName() != null) {
            db.update("update playlist set songName = ? where songId = ?", song.getSongName(), id);
        }
        if (song.getLyricist() != null) {
            db.update("update playlist set lyricist = ? where songId=?", song.getLyricist(), id);
        }
        if (song.getSinger() != null) {
            db.update("update playlist set singer = ? where songId = ?", song.getSinger(), id);
        }
        if (song.getMusicDirector() != null) {
            db.update("update playlist set musicDirector = ? where songId = ?", song.getMusicDirector(), id);
        }
        return getSongById(id);
    }

}
