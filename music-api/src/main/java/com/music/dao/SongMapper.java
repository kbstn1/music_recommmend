package com.music.dao;

import com.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKeyWithBLOBs(Song record);

    int updateByPrimaryKey(Song record);

    int updateSongMsg(Song record);

    int updateSongUrl(Song record);

    int updateSongPic(Song record);

    int deleteSong(Integer id);

    List<Song> allSong();

    List<Song> songOfId(Integer id);

    List<Song> songOfSingerName(String name);

    List<Song> songOfName(String name);

    List<Song> collectSongOfUser(Integer id);

    List<Song> selectByKeyword(String keyword);

    List<Song> selectPlayRank();

    List<Song> selectDownloadRank();

    List<Song> selectCollectRank();

    List<Song> selectHotRank();
}
