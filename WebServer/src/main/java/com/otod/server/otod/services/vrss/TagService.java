package com.otod.server.otod.services.vrss;


import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.respository.vrss.FileInfoRepository;
import com.otod.server.otod.respository.vrss.FileListRepository;
import com.otod.server.otod.respository.vrss.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository t_tagRepository;
    @Autowired
    private FileInfoRepository t_fileInfoRepository;
    @Autowired
    private FileListRepository t_fileListRepository;
    //添加文件标签
    public void addFileTag(Integer file_id,Integer[] tag_id){
        FileInfo file=t_fileInfoRepository.findById(file_id).get();
        for (Integer t:tag_id){
            Tag tag= t_tagRepository.findById(t).get();
            file.getTag().add(tag);
            t_fileInfoRepository.save(file);
        }
    }
    //删除文件标签
    public void deleteFileTag(Integer file_id,Integer[] tag_id){
        FileInfo file=t_fileInfoRepository.findById(file_id).get();
        for (Integer t:tag_id){
            Tag tag= t_tagRepository.findById(t).get();
            file.getTag().remove(tag);
            t_fileInfoRepository.save(file);
        }
    }
    //添加专辑标签
    public void addFileListTag(Integer filelist_id,Integer[] tag_id){
        FileList filelist= t_fileListRepository.findById(filelist_id).get();
        for (Integer t:tag_id){
            Tag tag= t_tagRepository.findById(t).get();
            filelist.getTag().add(tag);
            t_fileListRepository.save(filelist);
        }
    }
    //删除专辑标签
    public void deleteFileListTag(Integer filelist_id,Integer[] tag_id){
        FileList filelist= t_fileListRepository.findById(filelist_id).get();
        for (Integer t:tag_id){
            Tag tag= t_tagRepository.findById(t).get();
            filelist.getTag().add(tag);
            t_fileListRepository.save(filelist);
        }
    }
    //加载所有标签
    public List<Tag> findAllTag(){
        return (List<Tag>) t_tagRepository.findAll();
    }
}
