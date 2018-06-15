package com.otod.server.otod.controller.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.services.vrss.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/vrss/FileInfo")
public class FileInfoController {
    @Autowired
    private FileInfoService fileInfoService;

    @RequestMapping(value = "/get")
    public FileInfo getfile(@RequestParam("file_id") Integer file_id){
        return fileInfoService.getFile(file_id);
    }
    @RequestMapping(value = "/upload")
    public Integer uploadfile(@RequestParam("name") String name,
                           @RequestParam("uploader_id") Integer uploader_id,
                           @RequestParam("size") Double size,
                           @RequestParam("type") String type,
                           @RequestParam("description") String description,
                           @RequestParam("file") MultipartFile file){
        return fileInfoService.uploadFile(name,uploader_id,size,type,description,file);
    }
    @RequestMapping(value = "/delete")
    public void deletefile(@RequestParam("file_id") Integer file_id){
        fileInfoService.deleteFile(file_id);
    }
    @RequestMapping(value = "/update")
    public void updatefile(@RequestParam("file_id") Integer file_id,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description){
        fileInfoService.updateFile(file_id,name,description);
    }
    @RequestMapping(value = "/love")
    public void lovefile(@RequestParam("user_id") Integer user_id,
                         @RequestParam("file_id") Integer file_id){
        fileInfoService.loveFile(user_id,file_id);
    }
    @RequestMapping(value = "/lovestate")
    public Boolean lovestate(@RequestParam("user_id") Integer user_id,
                              @RequestParam("file_id") Integer file_id){
        return fileInfoService.lovaState(user_id,file_id);
    }
    @RequestMapping(value = "/view")
    public void viewfile(@RequestParam("file_id") Integer file_id){
        fileInfoService.viewFile(file_id);
    }
    @RequestMapping(value = "/filetag")
    @ResponseBody
    public List<Tag> filetag(@RequestParam("file_id") Integer file_id){
        return fileInfoService.findFileTag(file_id);
    }
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<FileInfo> listfile(@RequestParam(value = "user_id",defaultValue = "0") Integer user_id,
                                   @RequestParam(value = "tag_id",defaultValue = "0") Integer tag_id,
                                   @RequestParam(value = "key",defaultValue = "null") String key,
                                   @RequestParam(value = "type",defaultValue = "0") Integer type){
        List<FileInfo> l=null;
        switch (type){
            case 1:
                l=fileInfoService.findAllFile();
                break;
            case 2:
                l=fileInfoService.findFileByLove();
                break;
            case 3:
                l=fileInfoService.findFileByViews();
                break;
            case 4:
                l=fileInfoService.findFileByScore();
                break;
            case 5:
                l=fileInfoService.findFileByDownload();
                break;
            case 6:
                l=fileInfoService.findFileLatest();
                break;
            case 7:
                l=fileInfoService.findFileByTag(tag_id);
                break;
            case 8:
                l=fileInfoService.findFileByKey(key);
                break;
            case 9:
                l=fileInfoService.findFileByUser(user_id);
                break;
            case 10:
                l=fileInfoService.findLove(user_id);
                break;
            case 0:
                break;
        }
        return l;
    }
}
