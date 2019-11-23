package com.blog.backend.repository;

import com.blog.backend.model.Follow;
import com.blog.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByAuthor(String author);
    List<Follow> findAllByFollower(String follower);
    Boolean existsByFollowerAndAuthor(String follower, String author);
    Optional<Follow> findByFollowerAndAuthor(String follower, String author);
}
