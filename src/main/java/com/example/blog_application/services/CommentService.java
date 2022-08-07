package com.example.blog_application.services;

import com.example.blog_application.payloads.CommentDto;

public interface CommentService {


    CommentDto createComment(CommentDto commentDto, Integer postId);
    void deleteComment (Integer commentId);

}
