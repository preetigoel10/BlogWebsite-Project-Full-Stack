package com.blog.backend.repository;

import com.blog.backend.model.Comments;
import com.blog.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentsRepository  extends JpaRepository<Comments, Long> {
  List<Comments> findAllByPostId(Long id);
  List<Comments> findAllByPostIdOrderByDateDesc(Long id);
}
