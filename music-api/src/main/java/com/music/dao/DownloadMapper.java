package com.music.dao;

import com.music.domain.Download;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadMapper {
    int insertSelective(Download download);

    List<Download> allDownload();
}
