package com.blog.backend.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
    @Entity
    @Table(name="Follow")
    @EntityListeners(AuditingEntityListener.class)
    public class Follow implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Long followerId;
        private String follower;
        private Long authorId;
        private String author;

        public Follow() {
        }

        public Long getFollowerId() {
            return followerId;
        }

        public void setFollowerId(Long followerId) {
            this.followerId = followerId;
        }

        public Long getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Long authorId) {
            this.authorId = authorId;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFollower() {
            return follower;
        }

        public void setFollower(String follower) {
            this.follower = follower;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
