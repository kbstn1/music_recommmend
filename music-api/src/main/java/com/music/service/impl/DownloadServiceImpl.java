package com.music.service.impl;

import com.music.dao.DownloadMapper;
import com.music.domain.Download;
import com.music.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadServiceImpl implements DownloadService {
    @Autowired
    private DownloadMapper downloadMapper;

    @Override
    public boolean addDownload(Download download){ return downloadMapper.insertSelective(download) > 0 ? true:false; }

    @Override
    public List<Download> allDownload(){ return downloadMapper.allDownload(); }
}
