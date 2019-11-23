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
import java.util.*;

@Service
public class PostService {
    private UsersRepository usersRepository;
    private PostsRepository postsRepository;
    private FollowService followService;
    private CommentsRepository commentsRepository;
    @Autowired
    public PostService(UsersRepository usersRepository, PostsRepository postsRepository, FollowService followService, CommentsRepository commentsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
        this.followService = followService;
        this.commentsRepository = commentsRepository;
    }




    public void createPost(Post post, Principal principal){
        Users users = usersRepository.findByUsername(principal.getName()).get();
        Post post1 = new Post();
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setUsername(principal.getName());
        post1.setUserId(users.getId());
        post1.setCreatedOn(new Date());
        post1.setCategory(post.getCategory());
        post1.setIsPrivate(post.getIsPrivate());
        post1.setLikes(0L);
        postsRepository.saveAndFlush(post1);
    }
    public void updatePost(Long id,Post post){
        Post post1 =  postsRepository.findById(id).get();
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setCategory(post.getCategory());
        post1.setUpdatedOn(new Date());
        post1.setIsPrivate(post.getIsPrivate());
        postsRepository.saveAndFlush(post1);
    }
    public List<Post> showAllPosts() {
        List<Post> posts = postsRepository.findAllByOrderByCreatedOnDesc();
        return posts;
    }
    public List<Post> showAllPostsByPrivacy(Principal principal) {
        List<Post> posts = postsRepository.findAllByUsernameOrIsPrivateOrderByCreatedOnDesc(principal.getName(),0);
        List<Post> private_posts = postsRepository.findAllByIsPrivateOrderByCreatedOnDesc(1);
        for(int i=0;i<private_posts.size();i++) {
            if (followService.isFollowing(principal, private_posts.get(i).getUserId())) {
                posts.add(postsRepository.findById(private_posts.get(i).getId()).get());
            }
        }
        Collections.sort(posts);
        return posts;
    }
    public List<Post>showAllPostsByUser(Principal principal) {
        Users users = usersRepository.findByUsername(principal.getName()).get();
        return postsRepository.findByUserIdOrderByCreatedOnDesc(users.getId());
    }
    public List<Post>showAllPostsForSearch(Principal principal,String username) {
        List<Post> posts = postsRepository.findAllByUsernameOrTitleOrContentOrCreatedOnAndIsPrivateOrderByCreatedOnDesc(username,0);
        List<Post> private_posts = postsRepository.findAllByUsernameOrTitleOrContentOrCreatedOnAndIsPrivateOrderByCreatedOnDesc(username,1);
        for(int i=0;i<private_posts.size();i++) {
            if (followService.isFollowing(principal, private_posts.get(i).getUserId())) {
                posts.add(postsRepository.findById(private_posts.get(i).getId()).get());
            }
        }
        Collections.sort(posts);
        return posts;
    }
    public Optional<Post> getPost(Long id) {
       return postsRepository.findById(id);
    }
    public List<Post> showAllPostsByUserId(Principal principal,Long id) {
        List<Post> posts;
        if(followService.isFollowing(principal,id))
        {
             posts = postsRepository.findByUserIdOrderByCreatedOnDesc(id);
        }
        else {
             posts = postsRepository.findByUserIdAndIsPrivateOrderByCreatedOnDesc(id,0);
        }

        return posts;
    }
    public void deletePost(Long id){
        postsRepository.deleteById(id);
       commentsRepository.deleteAll(commentsRepository.findAllByPostId(id));
    }

}
