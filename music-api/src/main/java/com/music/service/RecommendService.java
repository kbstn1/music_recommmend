package com.music.service;

import com.music.domain.Song;
import com.music.recommend.dto.ProductDTO;
import com.music.recommend.dto.RelateDTO;

import java.util.List;

public interface RecommendService {
    List<RelateDTO> getRankData();

    List<Song> recommendSongByRank(Integer userId);

    List<ProductDTO> getSongData();

}
