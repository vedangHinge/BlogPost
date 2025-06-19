package com.cdac.service;

import java.util.List;
import java.util.UUID;

import com.cdac.Dto.PostDto;
import com.cdac.entity.CreatePostRequest;
import com.cdac.entity.Post;
import com.cdac.entity.User;

public interface PostService{
List<PostDto> getAllPosts(UUID categoryId,UUID tagId);
List<PostDto> getDraftPosts(User user);
Post createPost(User user,CreatePostRequest createPostRequest);
}
