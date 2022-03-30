package com.music.service;

import com.music.domain.Download;

import java.util.List;

public interface DownloadService {
    boolean addDownload(Download download);

    List<Download> allDownload();
}
