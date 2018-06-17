package com.otod.server.otod.controller.vrss;

import com.otod.server.otod.model.vrss.FileInfo;
import com.otod.server.otod.model.vrss.FileList;
import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.services.vrss.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/vrss/FileList")
public class FileListController {
    @Autowired
    private FileListService fileListService;
    @RequestMapping(value = "/get")
    public FileList getfilelist(@RequestParam("creator_id") Integer filelist_id){
        return fileListService.getFileList(filelist_id);
    }
    @RequestMapping(value = "/add")
    public Integer addfilelist(@RequestParam("name") String name,
                            @RequestParam("creator_id") Integer creator_id,
                            @RequestParam("description") String description){
        return fileListService.addFileList(name,creator_id,description);
    }
    @RequestMapping(value = "/delete")
    public void deletefilelist(@RequestParam("filelist_id") Integer filelist_id){
        fileListService.deleteFileList(filelist_id);
    }
    @RequestMapping(value = "/update")
    public void updatefilelist(@RequestParam("filelist_id") Integer filelist_id,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description){
        fileListService.updateFileList(filelist_id,name,description);
    }
    @RequestMapping(value = "/love")
    public void lovefilelist(@RequestParam("user_id") Integer user_id,
                             @RequestParam("filelist_id") Integer filelist_id){
        fileListService.loveFileList(user_id,filelist_id);
    }
    @RequestMapping(value = "/lovestate")
    public Boolean lovestate(@RequestParam("user_id") Integer user_id,
                             @RequestParam("filelist_id") Integer filelist_id){
        return fileListService.loveState(user_id,filelist_id);
    }
    @RequestMapping(value = "/view")
    public void viewfilelist(@RequestParam("filelist_id") Integer filelist_id){
        fileListService.viewFileList(filelist_id);
    }
    @RequestMapping(value = "/addfile")
    public void addfile(@RequestParam("filelist_id") Integer filelist_id,
                        @RequestParam("file_id") Integer file_id){
        fileListService.addFile(filelist_id,file_id);
    }
    @RequestMapping(value = "/deletefile")
    public void deletefile(@RequestParam("filelist_id") Integer filelist_id,
                            @RequestParam("file_id") Integer file_id){
        fileListService.deleteFile(filelist_id,file_id);
    }
    @RequestMapping(value = "/listfile")
    @ResponseBody
    public List<FileInfo> listfile(@RequestParam("filelist_id") Integer filelist_id){
        return fileListService.findFileInList(filelist_id);
    }
    @RequestMapping(value = "/filelisttag")
    @ResponseBody
    public List<Tag> filelisttag(@RequestParam("filelist_id") Integer filelist_id){
        return fileListService.findFileListTag(filelist_id);
    }
    @RequestMapping(value = "/listfilelist")
    @ResponseBody
    public List<FileList> listfilelist(@RequestParam(value = "user_id",defaultValue = "0") Integer user_id,
                                       @RequestParam(value = "tag_id",defaultValue = "0") Integer tag_id,
                                       @RequestParam(value = "key",defaultValue = "null") String key,
                                       @RequestParam(value = "type",defaultValue = "0") Integer type){
        List<FileList> l=null;
        switch (type){
            case 1:
                l=fileListService.findAllFileList();
                break;
            case 2:
                l=fileListService.findFileListByLove();
                break;
            case 3:
                l=fileListService.findFileListByViews();
                break;
            case 4:
                l=fileListService.findFileListByScore();
                break;
            case 5:
                l=fileListService.findFileListByTag(tag_id);
                break;
            case 6:
                l=fileListService.findFileListByKey(key);
                break;
            case 7:
                l=fileListService.findFileListByUser(user_id);
                break;
            case 8:
                l=fileListService.findLove(user_id);
                break;
            case 0:
                break;
        }
        return l;
    }
}
