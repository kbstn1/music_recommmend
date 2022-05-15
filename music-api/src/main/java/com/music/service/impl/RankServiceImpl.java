package com.music.service.impl;

import com.music.dao.RankMapper;
import com.music.domain.Rank;
import com.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankMapper rankMapper;

    @Override
    public boolean addRankRecord(Rank record) { return rankMapper.insert(record) > 0 ? true:false;}

    @Override
    public boolean updateRankRecord(Rank record) { return rankMapper.updateRankMsg(record) > 0 ? true:false;}

    @Override
    public List<Rank> rankOfSongByConsumer(Rank record) { return rankMapper.rankOfSongByConsumer(record);}

}
