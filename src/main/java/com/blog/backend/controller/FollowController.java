package com.blog.backend.controller;

import com.blog.backend.model.Follow;
import com.blog.backend.model.Users;
import com.blog.backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/follow")
public class FollowController {
    private FollowService followService;

    @Autowired
    public FollowController(FollowService followService) {
        this.followService = followService;
    }
    @PostMapping(value = "/follow/{id}",produces = "application/json")
    public Users follow(Principal principal, @RequestBody @PathVariable Long id) {
        return followService.follow(principal,id);
    }
    @PostMapping(value = "/unfollow/{id}",produces = "application/json")
    public Users unFollow(Principal principal, @RequestBody @PathVariable Long id) {
        return followService.unFollow(principal,id);
    }
    @GetMapping(value = "/getFollowing", produces = "application/json")
    public List<Follow> getFollowing(Principal principal){
        return followService.getFollowing(principal);
    }
    @GetMapping(value = "/getFollowers", produces = "application/json")
    public List<Follow> getFollowers(Principal principal){
        return followService.getFollowers(principal);
    }
    @GetMapping(value = "/isFollowing/{id}",produces = "application/json")
    public Boolean isFollowing(Principal principal,@RequestBody @PathVariable Long id) {
        return followService.isFollowing(principal,id);
    }
}
