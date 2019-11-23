package com.blog.backend.controller;

import com.blog.backend.model.Comments;
import com.blog.backend.model.Post;
import com.blog.backend.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comment")
public class CommentsController {
    private CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }
    @GetMapping(path = "/viewComments/{id}", produces = "application/json")
    public List<Comments> viewComments(@PathVariable(value = "id") Long id) {
        return commentsService.viewComments(id);
    }

    @PostMapping(path = "/createComment/{id}", consumes = "application/json", produces = "application/json")
    public List<Comments> createComment(@PathVariable(value = "id") Long id, @RequestBody Comments comment,
                                        Principal principal) {
        return commentsService.createComment(comment, principal, id);
    }

    @DeleteMapping(path = "/deleteComment/{postId}/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public List<Comments> deleteComment(@PathVariable("postId") Long postId,@PathVariable("commentId") Long commentId) {
        return commentsService.deleteComment( postId, commentId);
    }

//    @PostMapping(path = "/like/{id}", consumes = "application/json", produces = "application/json")
//    public List<Comments> likeComment(@PathVariable(value = "id") Long id) {
//        return commentsService.like(id);
//    }
//
//    @PostMapping(path = "/unlike/{id}", consumes = "application/json", produces = "application/json")
//    public List<Comments> unlikeComment(@PathVariable(value = "id") Long id) {
//        return commentsService.unLike(id);
//    }

}
