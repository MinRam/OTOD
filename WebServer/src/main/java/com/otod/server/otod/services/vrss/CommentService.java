package com.otod.server.otod.services.vrss;


import com.otod.server.otod.model.vrss.Comment;
import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import com.otod.server.otod.model.vrss.VrssUser;
import com.otod.server.otod.respository.vrss.CommentRepository;
import com.otod.server.otod.respository.vrss.FileInfoRepository;
import com.otod.server.otod.respository.vrss.FileListRepository;
import com.otod.server.otod.respository.vrss.VrssUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository c_commentRepository;

    @Autowired
    private VrssUserRepository c_Vrss_userRepository;

    @Autowired
    private FileInfoRepository c_fileInfoRepository;

    @Autowired
    private FileListRepository c_fileListRepository;

    //用户进行评论
    public Comment addComment(String comment, Integer user, Integer reply_id, Integer file_id, Integer filelist_id, Double score){
        Comment c=new Comment();
        c.setComment(comment);
        VrssUser u= c_Vrss_userRepository.findById(user).get();
        if(reply_id!=0){
            VrssUser r= c_Vrss_userRepository.findById(reply_id).get();
            c.setReply(r);
        }
        c.setVrssUser(u);
        FileInfo file= null;
        FileList filelist= null;
        if(filelist_id==0){
            file=c_fileInfoRepository.findById(file_id).get();
            c.setFile(file);
        }
        else{
            filelist=c_fileListRepository.findById(filelist_id).get();
            c.setFilelist(filelist);
        }
        Date d=new java.sql.Date(new Date().getTime());
        c.setCreate_time(d);
        c.setScore(score);
        c.setLove(0);

        if(filelist_id==0){
            List<Comment> l= c_commentRepository.findAllByFile(file);
            double cnt=l.size();
            double all=0;
            for(Comment co:l){
                all+=co.getScore();
            }
            file.setScore((all+score)/(cnt+1.0));
            c_fileInfoRepository.save(file);
        }
        else{
            List<Comment> l= c_commentRepository.findAllByFilelist(filelist);
            double cnt=l.size();
            double all=0;
            for(Comment co:l){
                all+=co.getScore();
            }
            filelist.setScore((all+score)/(cnt+1));
            c_fileListRepository.save(filelist);
        }

        return c_commentRepository.save(c);
    }

    //用户对评论进行点赞
    public void love(Integer id){
        Comment c= c_commentRepository.findById(id).get();
        c.setLove(c.getLove()+1);
        c_commentRepository.save(c);
    }

    //加载文件评论
    public List<Comment> findCommentByFile(Integer file_id){
        return c_commentRepository.findAllByFile(c_fileInfoRepository.findById(file_id).get());
    }

    //加载专辑评论
    public List<Comment> findCommentByFileList(Integer filelist_id){
        return c_commentRepository.findAllByFilelist(c_fileListRepository.findById(filelist_id).get());
    }

}
