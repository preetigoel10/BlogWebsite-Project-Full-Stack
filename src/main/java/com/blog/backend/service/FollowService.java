package com.blog.backend.service;

import com.blog.backend.model.Follow;
import com.blog.backend.model.Users;
import com.blog.backend.repository.FollowRepository;
import com.blog.backend.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FollowService {
    private FollowRepository followRepository;
    private UsersRepository usersRepository;
@Autowired
    public FollowService(FollowRepository followRepository, UsersRepository usersRepository) {
        this.followRepository = followRepository;
        this.usersRepository = usersRepository;
    }
   public Users follow(Principal principal,Long id){
    Users follower = usersRepository.findByUsername(principal.getName()).get();
    Users author = usersRepository.findById(id).get();
       String authorName = author.getUsername();
       Optional<Follow> optionalFollow = followRepository.findByFollowerAndAuthor(principal.getName(),authorName);
       Follow follow;
        if(!optionalFollow.isPresent() && !principal.getName().equals(authorName)) {
            follow = new Follow();
            follow.setFollowerId(follower.getId());
            follow.setAuthorId(author.getId());
            follow.setAuthor(authorName);
            follow.setFollower(principal.getName());
            author.setFollowers(author.getFollowers() + 1);
            follower.setFollowing(follower.getFollowing() + 1);
            followRepository.saveAndFlush(follow);
        }
        return author;
    }

   public Users unFollow(Principal principal,Long id){
       Users unfollow = usersRepository.findByUsername(principal.getName()).get();
    Users author = usersRepository.findById(id).get();
       String authorName = author.getUsername();
    Optional<Follow> optionalFollow = followRepository.findByFollowerAndAuthor(principal.getName(),authorName);
    optionalFollow.ifPresent(follow -> followRepository.delete(follow));
    author.setFollowers(author.getFollowers() - 1);
       unfollow.setFollowing(unfollow.getFollowing() - 1);
       usersRepository.saveAndFlush(author);
       usersRepository.saveAndFlush(unfollow);
    return author;
}
  public   List<Follow> getFollowers(Principal principal) {
        return followRepository.findAllByAuthor(principal.getName());
    }
   public List<Follow> getFollowing(Principal principal) {
        return followRepository.findAllByFollower(principal.getName());
    }
    public Boolean isFollowing(Principal principal,Long id) {
    Users author = usersRepository.findById(id).get();
    String authorName = author.getUsername();
    return followRepository.existsByFollowerAndAuthor(principal.getName(), authorName);
    }

}
