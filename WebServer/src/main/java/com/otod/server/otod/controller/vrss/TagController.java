package com.otod.server.otod.controller.vrss;

import com.otod.server.otod.model.vrss.Tag;
import com.otod.server.otod.services.vrss.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/vrss/Tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @RequestMapping(value = "/addfiletag")
    public void addfiletag(@RequestParam("file_id") Integer file_id,
                           @RequestParam("tag_id") Integer[] tag_id){
        tagService.addFileTag(file_id,tag_id);
    }
    @RequestMapping(value = "/deletefiletag")
    public void deletefiletag(@RequestParam("file_id") Integer file_id,
                              @RequestParam("tag_id") Integer[] tag_id){
        tagService.deleteFileTag(file_id,tag_id);
    }
    @RequestMapping(value = "/addfilelisttag")
    public void addfilelisttag(@RequestParam("filelist_id") Integer filelist_id,
                           @RequestParam("tag_id") Integer[] tag_id){
        tagService.addFileListTag(filelist_id,tag_id);
    }
    @RequestMapping(value = "/deletefilelisttag")
    public void deletefilelisttag(@RequestParam("filelist_id") Integer filelist_id,
                               @RequestParam("tag_id") Integer[] tag_id){
        tagService.deleteFileListTag(filelist_id,tag_id);
    }
    @RequestMapping(value = "/listtag")
    @ResponseBody
    public List<Tag> listtag(){
        return tagService.findAllTag();
    }
}
