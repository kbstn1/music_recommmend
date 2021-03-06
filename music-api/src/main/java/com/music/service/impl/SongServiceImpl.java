package com.music.service.impl;

import com.music.dao.SongMapper;
import com.music.domain.Song;
import com.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> allSong()
    {
        return songMapper.allSong();
    }

    @Override
    public boolean addSong(Song song)
    {

        return songMapper.insertSelective(song) > 0?true:false;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateSongMsg(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateSongUrl(song) >0 ?true:false;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateSongPic(song) >0 ?true:false;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteSong(id) >0 ?true:false;
    }


    @Override
    public List<Song> songOfId(Integer id)

    {
        return songMapper.songOfId(id);
    }

    @Override
    public List<Song> songOfSingerName(String name)

    {
        return songMapper.songOfSingerName(name);
    }

    @Override
    public List<Song> songOfName(String name)

    {
        return songMapper.songOfName(name);
    }

    @Override
    public List<Song> collectSongOfUser(Integer userId) { return songMapper.collectSongOfUser(userId);}

    @Override
    public List<Song> selectByKeyword(String keyword) { return songMapper.selectByKeyword(keyword);}

    @Override
    public List<Song> selectPlayRank() { return songMapper.selectPlayRank();}

    @Override
    public List<Song> selectDownloadRank() { return songMapper.selectDownloadRank();}

    @Override
    public List<Song> selectCollectRank() { return songMapper.selectCollectRank();}

    @Override
    public List<Song> selectHotRank() { return songMapper.selectHotRank();}
}
