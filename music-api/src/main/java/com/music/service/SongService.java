package com.music.service;

import com.music.domain.Song;

import java.util.List;

public interface SongService {

    boolean addSong (Song song);

    boolean updateSongMsg(Song song);

    boolean updateSongUrl(Song song);

    boolean updateSongPic(Song song);

    boolean deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfId(Integer id);

    List<Song> songOfSingerName(String name);

    List<Song> songOfName(String name);

    List<Song> collectSongOfUser(Integer userId);

    List<Song> selectByKeyword(String keyword);
}
