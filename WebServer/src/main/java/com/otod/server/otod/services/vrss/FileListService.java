package com.otod.server.otod.services.vrss;


import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.model.vrss.VrssUser;
import com.otod.server.otod.respository.vrss.FileInfoRepository;
import com.otod.server.otod.respository.vrss.FileListRepository;
import com.otod.server.otod.respository.vrss.TagRepository;
import com.otod.server.otod.respository.vrss.VrssUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileListService {
    @Autowired
    private FileListRepository fl_fileListRepository;
    @Autowired
    private VrssUserRepository fl_Vrss_userRepository;
    @Autowired
    private FileInfoRepository fl_fileInfoRepository;
    @Autowired
    private TagRepository fl_tagRepository;
    //获取专辑信息
    public FileList getFileList(Integer filelist_id){
        return fl_fileListRepository.findById(filelist_id).get();
    }
    //用户新建专辑
    public void addFileList(String name,Integer creator_id,String description){
        FileList filelist=new FileList();
        filelist.setName(name);
        VrssUser vrssUser = fl_Vrss_userRepository.findById(creator_id).get();
        filelist.setVrssUser(vrssUser);
        Date date=new java.sql.Date(new Date().getTime());
        filelist.setCreate_time(date);
        filelist.setDescription(description);
        filelist.setScore(0.);
        filelist.setViews(0);
        filelist.setLove(0);
        fl_fileListRepository.save(filelist);
    }
    //用户删除专辑
    public void deleteFileList(Integer filelist_id){
        fl_fileListRepository.deleteById(filelist_id);
    }
    //用户修改专辑信息
    public void updateFileList(Integer filelist_id,String name,String description){
        FileList filelist= fl_fileListRepository.findById(filelist_id).get();
        filelist.setName(name);
        Date date=new java.sql.Date(new Date().getTime());
        filelist.setCreate_time(date);
        filelist.setDescription(description);
        fl_fileListRepository.save(filelist);
    }
    //用户收藏专辑
    public void loveFileList(Integer user_id, Integer filelist_id){
        if (loveState(user_id,filelist_id)){
            FileList filelist= fl_fileListRepository.findById(filelist_id).get();
            VrssUser vrssUser = fl_Vrss_userRepository.findById(user_id).get();
            filelist.setLove(filelist.getLove()-1);
            vrssUser.getFilelist().remove(filelist);
            fl_Vrss_userRepository.save(vrssUser);
            fl_fileListRepository.save(filelist);
        }
        else{
            FileList filelist= fl_fileListRepository.findById(filelist_id).get();
            VrssUser vrssUser = fl_Vrss_userRepository.findById(user_id).get();
            filelist.setLove(filelist.getLove()+1);
            vrssUser.getFilelist().add(filelist);
            fl_Vrss_userRepository.save(vrssUser);
            fl_fileListRepository.save(filelist);
        }
    }
    public Boolean loveState(Integer user_id, Integer filelist_id){
        FileList fileList= fl_fileListRepository.findById(filelist_id).get();
        VrssUser vrssUser = fl_Vrss_userRepository.findById(user_id).get();
        List<VrssUser> l=fl_Vrss_userRepository.findByFilelist(fileList);
        if(l.size()==0)  return false;
        for (VrssUser v:l){
            if(v.getUser_id().equals(vrssUser.getUser_id())) return true;
        }
        return false;
    }
    //浏览文件
    public void viewFileList(Integer filelist_id){
        FileList filelist= fl_fileListRepository.findById(filelist_id).get();
        filelist.setViews(filelist.getViews()+1);
        fl_fileListRepository.save(filelist);
    }
    //在专辑中添加文件
    public void addFile(Integer filelist_id,Integer file_id){
        FileList filelist= fl_fileListRepository.findById(filelist_id).get();
        FileInfo file= fl_fileInfoRepository.findById(file_id).get();
        List<FileList> l=fl_fileListRepository.findByFile(file);
        if(l.size()>0)  return;
        filelist.getFile().add(file);
        fl_fileListRepository.save(filelist);
    }
    //在专辑中删除文件
    public void deleteFile(Integer filelist_id,Integer file_id){
        FileList filelist= fl_fileListRepository.findById(filelist_id).get();
        FileInfo file= fl_fileInfoRepository.findById(file_id).get();
        filelist.getFile().remove(file);
        fl_fileListRepository.save(filelist);
    }
    //查找专辑中的文件
    public List<FileInfo> findFileInList(Integer filelist_id){
        FileList filelist= fl_fileListRepository.findById(filelist_id).get();
        return (List<FileInfo>) filelist.getFile();
    }
    //加载专辑的标签
    public List<Tag> findFileListTag(Integer filelist_id){
        FileList filelist= fl_fileListRepository.findById(filelist_id).get();
        return (List<Tag>) filelist.getTag();
    }

    //加载全部专辑1
    public List<FileList> findAllFileList(){
        return fl_fileListRepository.findAll();
    }
    //根据收藏数列出专辑2
    public List<FileList> findFileListByLove(){
        return fl_fileListRepository.findAllByOrderByLove();
    }
    //根据浏览数数列出专辑3
    public List<FileList> findFileListByViews(){
        return fl_fileListRepository.findAllByOrderByViews();
    }
    //根据评分列出专辑4
    public List<FileList> findFileListByScore(){
        return fl_fileListRepository.findAllByOrderByScore();
    }
    //根据标签加载专辑5
    public List<FileList> findFileListByTag(Integer tag_id){
        return fl_fileListRepository.findAllByTag(fl_tagRepository.findById(tag_id).get());
    }
    //根据关键词搜索专辑6
    public List<FileList> findFileListByKey(String key){
        return fl_fileListRepository.findBykey(key);
    }
    //用户创建的专辑7
    public List<FileList> findFileListByUser(Integer user_id){
        return fl_fileListRepository.findAllByVrssUser(fl_Vrss_userRepository.findById(user_id).get());
    }
    //用户收藏的专辑8
    public List<FileList> findLove(Integer user_id){
        return fl_Vrss_userRepository.findById(user_id).get().getFilelist();
    }
}
