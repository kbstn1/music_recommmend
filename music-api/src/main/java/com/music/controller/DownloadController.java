package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.music.domain.Collect;
import com.music.domain.Download;
import com.music.service.impl.DownloadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class DownloadController {
    @Autowired
    private DownloadServiceImpl downloadService;

    //添加下载记录
    @ResponseBody
    @RequestMapping(value = "/download/add", method = RequestMethod.POST)
    public Object addDownload(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String song_id = req.getParameter("songId");
        if (song_id == ""){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "下载歌曲为空");
            return jsonObject;
        }
        Download download = new Download();
        download.setUserId(Integer.parseInt(user_id));
        download.setSongId(Integer.parseInt(song_id));
        download.setCreateTime(new Date());
        boolean res = downloadService.addDownload(download);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "下载成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "下载失败");
            return jsonObject;
        }
    }
}
