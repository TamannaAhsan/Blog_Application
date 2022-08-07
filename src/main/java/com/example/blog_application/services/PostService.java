package com.example.blog_application.services;

import com.example.blog_application.entities.Post;
import com.example.blog_application.payloads.PostDto;

import java.util.List;
import java.util.Set;

public interface PostService {
    PostDto createPost (PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost (PostDto postDto, Integer postId);
    void deletePost (Integer postId);
    Set<PostDto> getAllPost();
    PostDto getPostById (Integer postId);
    List<PostDto> getPostsByCategory (Integer categoryId);
    List<PostDto> getPostsByUser (Integer userId);
    List<PostDto> searchPosts (String keyword);

}
