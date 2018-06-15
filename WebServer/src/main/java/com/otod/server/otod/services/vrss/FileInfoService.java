package com.otod.server.otod.services.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileUtil;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.model.vrss.VrssUser;
import com.otod.server.otod.respository.vrss.CommentRepository;
import com.otod.server.otod.respository.vrss.FileInfoRepository;
import com.otod.server.otod.respository.vrss.TagRepository;
import com.otod.server.otod.respository.vrss.VrssUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileInfoService {
    @Autowired
    private FileInfoRepository fi_fileInfoRepository;
    @Autowired
    private VrssUserRepository fi_Vrss_userRepository;
    @Autowired
    private TagRepository fi_tagRepository;
    @Autowired
    private CommentRepository fi_commentRepository;

    private Integer copy(MultipartFile src, VrssUser vrssUser, FileInfo fileInfo){
        String webroot=System.getProperty("user.dir");
        String file_url= "/vrss/userfile/" + vrssUser.getUser_id()+"/"+fileInfo.getId()+"/"+src.getOriginalFilename();
        File dst =new File(webroot+file_url);
        try {
            FileUtils.copyInputStreamToFile(src.getInputStream(),dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInfo.setFile_url(file_url);
        return fi_fileInfoRepository.save(fileInfo).getId();
    }
    //获取文件信息
    public FileInfo getFile(Integer file_id){
        return fi_fileInfoRepository.findById(file_id).get();
    }
    //上传文件
    public Integer uploadFile(String name, Integer uploader_id, Double size, String type, String description, MultipartFile f){
        FileInfo file=new FileInfo();
        file.setName(name);
        VrssUser vrssUser = fi_Vrss_userRepository.findById(uploader_id).get();
        file.setVrssUser(vrssUser);
        file.setSize(size);
        file.setType(type);
        Date date=new java.sql.Date(new Date().getTime());
        file.setUploadTime(date);
        file.setDescription(description);
        file.setDownloadCnt(0);
        file.setScore(0.);
        file.setViews(0);
        file.setLove(0);
        return copy(f, vrssUser, fi_fileInfoRepository.save(file));
    }
    //删除文件
    public void deleteFile(Integer file_id){
        FileInfo file= fi_fileInfoRepository.findById(file_id).get();
        fi_commentRepository.deleteAllByFile(file);
        fi_fileInfoRepository.deleteById(file_id);
        try {
            if (FileUtil.isFileExist(file.getFile_url()))
                FileUtil.deleteFile(file.getFile_url());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //修改文件信息
    public void updateFile(Integer file_id,String name,String description){
        FileInfo file= fi_fileInfoRepository.findById(file_id).get();
        file.setName(name);
        file.setDescription(description);
        fi_fileInfoRepository.save(file);
    }
    //收藏文件
    public void loveFile(Integer user_id,Integer file_id){
        if (lovaState(user_id,file_id)){
            FileInfo file= fi_fileInfoRepository.findById(file_id).get();
            VrssUser vrssUser = fi_Vrss_userRepository.findById(user_id).get();
            file.setLove(file.getLove()-1);
            vrssUser.getFile().remove(file);
            fi_fileInfoRepository.save(file);
            fi_Vrss_userRepository.save(vrssUser);
        }
        else{
            FileInfo file= fi_fileInfoRepository.findById(file_id).get();
            VrssUser vrssUser = fi_Vrss_userRepository.findById(user_id).get();
            file.setLove(file.getLove()+1);
            vrssUser.getFile().add(file);
            fi_fileInfoRepository.save(file);
            fi_Vrss_userRepository.save(vrssUser);
        }
    }
    public Boolean lovaState(Integer user_id,Integer file_id){
        FileInfo file= fi_fileInfoRepository.findById(file_id).get();
        VrssUser vrssUser = fi_Vrss_userRepository.findById(user_id).get();
        List<VrssUser> l=fi_Vrss_userRepository.findByFile(file);
        if(l.size()>0)  return true;
        return false;
    }
    //浏览文件
    public void viewFile(Integer file_id){
        FileInfo file= fi_fileInfoRepository.findById(file_id).get();
        file.setViews(file.getViews()+1);
        fi_fileInfoRepository.save(file);
    }
    //加载文件标签
    public List<Tag> findFileTag(Integer file_id){
        FileInfo file= fi_fileInfoRepository.findById(file_id).get();
        return (List<Tag>) file.getTag();
    }
    //下载文件
    public String downloadFile(Integer file_id){
        FileInfo fileInfo= fi_fileInfoRepository.findById(file_id).get();
        return fileInfo.getFile_url();
    }

    //加载文件1
    public List<FileInfo> findAllFile(){
        return fi_fileInfoRepository.findAll();
    }
    //根据收藏数列出文件2
    public List<FileInfo> findFileByLove(){
        return fi_fileInfoRepository.findAllByOrderByLove();
    }
    //根据浏览数数列出文件3
    public List<FileInfo> findFileByViews(){
        return fi_fileInfoRepository.findAllByOrderByViews();
    }
    //根据评分列出文件4
    public List<FileInfo> findFileByScore(){
        return fi_fileInfoRepository.findAllByOrderByScore();
    }
    //根据下载数列出文件5
    public List<FileInfo> findFileByDownload(){
        return fi_fileInfoRepository.findAllByOrderByDownloadCnt();
    }
    //根据上传时间列出文件6
    public List<FileInfo> findFileLatest(){
        return fi_fileInfoRepository.findAllByOrderByUploadTime();
    }
    //根据标签加载文件7
    public List<FileInfo> findFileByTag(Integer tag_id){
        return fi_fileInfoRepository.findAllByTag(fi_tagRepository.findById(tag_id).get());
    }
    //根据关键词搜索文件8
    public List<FileInfo> findFileByKey(String key){
        return fi_fileInfoRepository.findBykey(key);
    }
    //用户上传的文件9
    public List<FileInfo> findFileByUser(Integer user_id){
        return fi_fileInfoRepository.findByVrssUser(fi_Vrss_userRepository.findById(user_id).get());
    }
    //用户收藏的文件10
    public List<FileInfo> findLove(Integer user_id){
        return fi_Vrss_userRepository.findById(user_id).get().getFile();
    }
}
