package com.hop.bookmark.dto;

import com.hop.bookmark.model.FolderModel;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FolderData {

    private Integer id;

    private String name;

    private Set<FolderData> children  = new HashSet<FolderData>();

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FolderData> getChildren() {
        return children;
    }

    public void setChildren(Set<FolderData> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FolderData)) return false;
        FolderData that = (FolderData) o;
        return getId().equals(that.getId()) &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
