package com.music.service;

import com.music.domain.Play;

import java.util.List;

public interface PlayService {
    boolean addPlay(Play play);

    List<Play> allPlay();
}
