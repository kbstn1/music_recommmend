package com.music.service.impl;

import com.music.dao.PlayMapper;
import com.music.domain.Play;
import com.music.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayServiceImpl implements PlayService {
    @Autowired
    private PlayMapper playMapper;

    @Override
    public boolean addPlay(Play play){ return playMapper.insertSelective(play) > 0 ? true:false; }

    @Override
    public List<Play> allPlay(){ return playMapper.allPlay(); }
}
