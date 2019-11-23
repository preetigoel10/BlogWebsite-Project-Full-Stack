package com.blog.backend.controller;

import com.blog.backend.model.Post;
import com.blog.backend.repository.PostsRepository;
import com.blog.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class PostController {
    public PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("/createPost")
    public ResponseEntity createPost(@Valid @RequestBody Post post, Principal principal){
        postService.createPost(post,principal);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/getAllPosts")
//    @ResponseBody
    public ResponseEntity<List<Post>> showAllPosts() {
//        return postsRepository.findAll();
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }
    @GetMapping("/getAllPostsByPrivacy")
    public ResponseEntity<List<Post>> showAllPostsByPrivacy(Principal principal) {
        return new ResponseEntity<>(postService.showAllPostsByPrivacy(principal), HttpStatus.OK);
    }
    @GetMapping("/getPost/{id}")
    public ResponseEntity<Optional<Post>> getPost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }
    @GetMapping("/getPostsByUser")
    public ResponseEntity<List<Post>> showAllPostsByUser(Principal principal) {
        return new ResponseEntity<>(postService.showAllPostsByUser(principal), HttpStatus.OK);
    }
    @GetMapping("/getPostsByUserId/{id}")
    public ResponseEntity<List<Post>> showAllPostsByUserId(@PathVariable @RequestBody Long id, Principal principal) {
        return new ResponseEntity<>(postService.showAllPostsByUserId(principal, id), HttpStatus.OK);
    }
    @GetMapping("/getAllPostsSearch/{username}")
    public ResponseEntity<List<Post>> showAllPostsForSearch(@PathVariable @RequestBody String username,  Principal principal) {
        return new ResponseEntity<>(postService.showAllPostsForSearch(principal,username), HttpStatus.OK);
    }
    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable @RequestBody Long id){
        postService.deletePost(id);
    }
    @PutMapping("/updatePost/{id}")
    public ResponseEntity updatePost(@PathVariable @RequestBody Long id,@Valid @RequestBody Post post){
        postService.updatePost(id,post);
        return new ResponseEntity(HttpStatus.OK);
    }
}
