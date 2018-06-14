package com.otod.server.otod.controller.vrss;


import com.otod.server.otod.model.vrss.Comment;
import com.otod.server.otod.services.vrss.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/vrss/Comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add")
    @ResponseBody
    public Comment add(@RequestParam("comment") String comment,
                       @RequestParam("user") Integer user,
                       @RequestParam(value = "reply_id",defaultValue = "0") Integer reply_id,
                       @RequestParam(value = "file_id",defaultValue = "0") Integer file_id,
                       @RequestParam(value = "filelist_id",defaultValue = "0") Integer filelist_id,
                       @RequestParam("score") Double score){
        return commentService.addComment(comment, user, reply_id, file_id, filelist_id, score);
    }

    @RequestMapping(value = "/like")
    public void like(@RequestParam("id") Integer id){
        commentService.love(id);
    }

    @RequestMapping(value = "/filecomment")
    @ResponseBody
    public List<Comment> filecomment(@RequestParam("id") Integer id){
        return commentService.findCommentByFile(id);
    }

    @RequestMapping(value = "/filelistcomment")
    @ResponseBody
    public List<Comment> filelistcomment(@RequestParam("id") Integer id){
        return commentService.findCommentByFileList(id);
    }
}
