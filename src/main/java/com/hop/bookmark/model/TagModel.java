package com.hop.bookmark.model;

import javax.persistence.*;

@Embeddable
@Table(name="tag")
public class TagModel {

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
