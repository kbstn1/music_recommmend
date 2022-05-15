package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.music.domain.Play;
import com.music.domain.Rank;
import com.music.service.impl.PlayServiceImpl;
import com.music.service.impl.RankServiceImpl;
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
public class PlayController {
    @Autowired
    private PlayServiceImpl playService;

    @Autowired
    private RankServiceImpl rankService;

    //添加播放记录
    @ResponseBody
    @RequestMapping(value = "/play/add", method = RequestMethod.POST)
    public Object addPlay(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String song_id = req.getParameter("songId");
        if (song_id == ""){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "播放歌曲为空");
            return jsonObject;
        }
        Play play = new Play();
        play.setUserId(Integer.parseInt(user_id));
        play.setSongId(Integer.parseInt(song_id));
        play.setCreateTime(new Date());
        boolean res = playService.addPlay(play);
        Rank rank = new Rank();
        rank.setUserId(Integer.parseInt(user_id));
        rank.setSongId(Integer.parseInt(song_id));
        if(!rankService.rankOfSongByConsumer(rank).isEmpty()){
            rank.setScore(rankService.rankOfSongByConsumer(rank).get(0).getScore()+1);
            rankService.updateRankRecord(rank);
        } else {
            rank.setScore(1);
            rankService.addRankRecord(rank);
        }
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "播放成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "播放失败");
            return jsonObject;
        }
    }
}
