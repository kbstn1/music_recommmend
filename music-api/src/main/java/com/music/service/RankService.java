package com.music.service;

import com.music.domain.Rank;

import java.util.List;

public interface RankService {

    boolean addRankRecord(Rank record);

    boolean updateRankRecord(Rank record);

    List<Rank> rankOfSongByConsumer(Rank record);
}
