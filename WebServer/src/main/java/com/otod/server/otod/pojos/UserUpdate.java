package com.otod.server.otod.pojos;

import java.util.List;

public class UserUpdate {
    private UserSimpleInfo author;
    private List<String> updateTags;
    private List<String> images;
    private String content;
    private UpdateOption updateOption;

    public UserUpdate() {
    }

    public UserSimpleInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserSimpleInfo author) {
        this.author = author;
    }

    public List<String> getUpdateTags() {
        return updateTags;
    }

    public void setUpdateTags(List<String> updateTags) {
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
