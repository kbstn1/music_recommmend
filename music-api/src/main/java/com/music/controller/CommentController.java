package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.music.domain.Comment;
import com.music.service.impl.CommentServiceImpl;
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
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    //提交评论
    @ResponseBody
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public Object addComment(HttpServletRequest req){

        JSONObject jsonObject = new JSONObject();
        String user_id = req.getParameter("userId");
        String song_id=req.getParameter("songId");
        String content = req.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(user_id));
        comment.setContent(content);
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }

    //获取所有评论列表
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public Object allComment(){
        return commentService.allComment();
    }

    //获得指定歌曲ID的评论列表
    @RequestMapping(value = "/comment/song/detail", method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest req){
        String songId = req.getParameter("songId");
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    //删除评论
    @RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest req){
        String id = req.getParameter("id");
        return commentService.deleteComment(Integer.parseInt(id));
    }

    //更新评论
    @ResponseBody
    @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
    public Object updateCommentMsg(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String id = req.getParameter("id").trim();
        String user_id = req.getParameter("userId").trim();
        String song_id = req.getParameter("songId").trim();
        String content = req.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(user_id));
        if (song_id == "") {
            comment.setSongId(null);
        } else {
            comment.setSongId(Integer.parseInt(song_id));
        }

        comment.setContent(content);

        boolean res = commentService.updateCommentMsg(comment);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }
}
