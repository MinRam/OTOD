package com.otod.server.otod.services.vrss;

import com.otod.server.otod.model.User;
import com.otod.server.otod.model.vrss.Download;
import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.VrssUser;
import com.otod.server.otod.respository.vrss.DownloadRepository;
import com.otod.server.otod.respository.vrss.FileInfoRepository;
import com.otod.server.otod.respository.vrss.VrssUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DownloadService {
    @Autowired
    private DownloadRepository d_downloadRepository;
    @Autowired
    private FileInfoRepository d_fileInfoRepository;
    @Autowired
    private VrssUserRepository d_Vrss_userRepository;
    //用户每次下载增加一条下载记录
    public void addDownload(Integer file_id,Integer user_id){
        VrssUser vrssUser=d_Vrss_userRepository.findById(user_id).get();
        FileInfo fileInfo=d_fileInfoRepository.findById(file_id).get();
        Download download=null;
        download=d_downloadRepository.findByVrssUserAndFile(vrssUser,fileInfo);
        if(download==null){
            download=new Download();
            download.setFile(d_fileInfoRepository.findById(file_id).get());
            download.setVrssUser(d_Vrss_userRepository.findById(user_id).get());
            Date date=new java.sql.Date(new Date().getTime());
            download.setDownload_time(date);
        }
        else{
            Date date=new java.sql.Date(new Date().getTime());
            download.setDownload_time(date);
        }
        d_downloadRepository.save(download);
    }

    //加载下载记录
    public List<Download> findDownload(Integer user_id){
        return d_downloadRepository.findAllByVrssUser(d_Vrss_userRepository.findById(user_id).get());
    }
}
