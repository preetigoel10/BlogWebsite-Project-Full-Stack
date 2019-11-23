package com.blog.backend.repository;

import com.blog.backend.model.Post;
import org.hibernate.validator.constraints.EAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Post, Long> {
 @Query(value = "select * from post p where (p.username like :name% || p.title like :name% || p.category like :name%|| p.content like :name% || p.created_on like  :name%) and p.is_private = :value",nativeQuery = true)
    List<Post> findAllByUsernameOrTitleOrContentOrCreatedOnAndIsPrivateOrderByCreatedOnDesc(String name, int value);
    List<Post> findAllByOrderByCreatedOnDesc();
    List<Post> findAllByIsPrivateOrderByCreatedOnDesc(int value);
    List<Post> findAllByUsernameOrIsPrivateOrderByCreatedOnDesc(String name, int value);
    List<Post> findByUserIdOrderByCreatedOnDesc(Long id);
    List<Post> findByUserIdAndIsPrivateOrderByCreatedOnDesc(Long id, int value);
  Optional<Post> findById(Long id);

}
