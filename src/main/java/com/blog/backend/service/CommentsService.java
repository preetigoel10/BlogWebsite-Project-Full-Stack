package com.blog.backend.service;

import com.blog.backend.model.Comments;
import com.blog.backend.model.Post;
import com.blog.backend.model.Users;
import com.blog.backend.repository.CommentsRepository;
import com.blog.backend.repository.PostsRepository;
import com.blog.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private CommentsRepository commentsRepository;

    @Autowired
    public CommentsService( CommentsRepository commentsRepository) {

        this.commentsRepository = commentsRepository;
    }
    public List<Comments> viewComments(Long id) {
      return commentsRepository.findAllByPostIdOrderByDateDesc(id);
    }
    public List<Comments> createComment(Comments comment, Principal principal, Long id) {
        comment.setDate(new Date());
        comment.setPostId(id);
        comment.setUsername(principal.getName());
        commentsRepository.saveAndFlush(comment);
        return commentsRepository.findAllByPostId(id);
    }
    public List<Comments> deleteComment( Long postId, Long commentId) {
        Comments comment = commentsRepository.findById(commentId).get();
        commentsRepository.delete(comment);
        return commentsRepository.findAllByPostId(postId);
    }
//    public List<Comments> like(Long commentId) {
//        Comments comment = commentsRepository.findById(commentId).get();
//        Post post = comment.getPost();
//
//        commentsRepository.saveAndFlush(comment);
//
//        return commentsRepository.findAllByPost(post);
//    }
//    public List<Comments> unLike(Long commentId) {
//        Comments comment = commentsRepository.findById(commentId).get();
//        Post post = comment.getPost();
//
//        comment.setLikes(comment.getLikes() - 1);
//        commentsRepository.saveAndFlush(comment);
//
//        return commentsRepository.findAllByPost(post);
//    }
}
