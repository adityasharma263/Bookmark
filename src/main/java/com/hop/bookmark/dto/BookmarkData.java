package com.hop.bookmark.dto;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BookmarkData {


    private Integer id;

    private String url;

    private String title;

    private Date date;

    private String imageUrl;

    private String description;

    private Set<FolderData> folders;

    private Set<TagData> tags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FolderData> getFolders() {
        return folders;
    }

    public void setFolders(Set<FolderData> folders) {
        this.folders = folders;
    }

    public Set<TagData> getTags() {
        return tags;
    }

    public void setTags(Set<TagData> tags) {
        this.tags = tags;
    }
}
