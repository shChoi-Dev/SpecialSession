package com.msa.Comments_api.controller;

import com.msa.Comments_api.record.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CommentController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/comments")
    public Comment[] comments(@RequestParam(defaultValue = "1") String postId) {
        Comment[] comments = restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments?postId="+postId, Comment[].class);
        return comments;
    }
}
