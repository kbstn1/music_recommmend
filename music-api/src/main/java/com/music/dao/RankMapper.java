package com.music.dao;

import com.music.domain.Rank;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RankMapper {

    int insert(Rank record);

    int updateRankMsg(Rank record);

    List<Rank> rankOfSongByConsumer(Rank record);

    List<Rank> selectRanks();
}
