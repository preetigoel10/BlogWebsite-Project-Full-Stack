package com.blog.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="post")
public class Post implements Comparable<Post>, Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column
    private String title;
    @Lob
    @NotBlank
    @Column
    private String content;
    @Column
    private Date createdOn;
    @Column
    private Date updatedOn;
    @Column
    private String category;
    @Column
    private String username;
    @Column
    private Long userId;
    @Column
    private int isPrivate = 0;
@Column
private long likes;

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public int getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(int isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int compareTo(Post o) {
        if(this.createdOn.compareTo(o.createdOn) < 0) return 1;
        if(this.createdOn.compareTo(o.createdOn) > 0) return -1;
        else return 0;
    }
}
