package com.blog.backend.repository;

import com.blog.backend.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPostId(Long postId);
    Optional<Likes> findAllByPostIdAndUsername(Long postId, String username);
}
