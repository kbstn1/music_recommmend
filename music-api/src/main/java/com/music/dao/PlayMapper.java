package com.music.dao;

import com.music.domain.Play;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayMapper {
    int insertSelective(Play play);

    List<Play> allPlay();
}
