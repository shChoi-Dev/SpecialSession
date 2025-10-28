package com.msa.posts_api.controller;

import com.msa.posts_api.record.Comment;
import com.msa.posts_api.record.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/posts")
    public Post[] posts() {
        Post[] posts = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
        return posts;
    }

    @GetMapping("/posts/{postId}")
    public Map post(@PathVariable String postId) {
        System.out.println("postId : " + postId);
        Post post = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/"+postId, Post.class);
        Comment[] comments = restTemplate.getForObject("http://localhost:8082/comments?postId="+postId, Comment[].class);
        Map map = new HashMap();
        map.put("post",post);
        map.put("comments",comments);
        return map;
    }
}