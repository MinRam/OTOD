package com.otod.server.otod.pojos;

import com.otod.server.otod.model.user.Update;
import com.otod.server.otod.model.user.UpdateTag;

import java.util.Arrays;
import java.util.List;

public class UserUpdate {
    private Integer updateId;
    private UserSimpleInfo author;
    private List<UpdateTag> updateTags;
    private List<String> images;
    private String content;
    private UpdateOption updateOption;

    public UserUpdate() {
    }

    public UserUpdate(Update update){
        this.updateId = update.getUpdateId().intValue();
        this.content = update.getContent();
        String image = update.getImages();
        if(image != null)
         this.images = Arrays.asList(update.getImages());
        this.updateTags = update.getTags();
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public UserSimpleInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserSimpleInfo author) {
        this.author = author;
    }

    public List<UpdateTag> getUpdateTags() {
        return updateTags;
    }

    public void setUpdateTags(List<UpdateTag> updateTags) {
        this.updateTags = updateTags;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UpdateOption getUpdateOption() {
        return updateOption;
    }

    public void setUpdateOption(UpdateOption updateOption) {
        this.updateOption = updateOption;
    }
}
