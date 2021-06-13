package com.hop.bookmark.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hop.bookmark.model.FolderModel;
import com.hop.bookmark.model.TagModel;


@Entity
@Table(name="bookmark")
public class BookmarkModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic(optional=false)
    private String url;

    @Basic(optional=false)
    private String title;

    @Basic(optional=false)
    private Date date;

    private String imageUrl;


    private String description;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "bookmark_folder",
            joinColumns = { @JoinColumn(name = "bookmark_id") },
            inverseJoinColumns = { @JoinColumn(name = "folder_id") }
    )
    private Set<FolderModel> folders = new HashSet<FolderModel>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<TagModel> tags = new HashSet<TagModel>();

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

    public Set<FolderModel> getFolders() {
        return folders;
    }

    public void setFolders(Set<FolderModel> folders) {
        this.folders = folders;
    }

    public Set<TagModel> getTags() {
        return tags;
    }

    public void setTags(Set<TagModel> tags) {
        this.tags = tags;
    }
}
