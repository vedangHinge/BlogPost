package com.cdac.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Dto.CreatePostRequestDto;
import com.cdac.Dto.PostDto;
import com.cdac.entity.CreatePostRequest;
import com.cdac.entity.Post;
import com.cdac.entity.User;
import com.cdac.service.PostService;
import com.cdac.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
@Slf4j
public class PostController {
private PostService postService;
private UserService userService;
private ModelMapper mapper;
@GetMapping
public ResponseEntity<?> getAllPosts(
		@RequestParam(required = false) UUID categoryId,
		@RequestParam(required = false) UUID tagId
		){
	return ResponseEntity.ok(postService.getAllPosts(categoryId,tagId));
}
@GetMapping("/drafts")
public ResponseEntity<?> getDrafts(@RequestAttribute UUID userId){
	User loggedInUser=userService.getById(userId);
	return ResponseEntity.ok(postService.getDraftPosts(loggedInUser));
}


//@PostMapping
//public ResponseEntity<?> createPost(
//		@Valid @RequestBody CreatePostRequestDto createPostReqDto)
//{
//	User loggedInUser=userService.getById(SecurityUtils.getCurrentUserId());
//	System.out.println(loggedInUser);
//	CreatePostRequest createPostRequest=mapper.map(createPostReqDto, CreatePostRequest.class);
//	Post createdPost = postService.createPost(loggedInUser, createPostRequest);
//	PostDto postCreated=mapper.map(createdPost, PostDto.class);
//	return ResponseEntity.status(HttpStatus.CREATED).body(postCreated);
//
//}
@PostMapping
public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequestDto createPostRequestDto,
        @RequestAttribute UUID userId) {
	System.out.println("UserID in post "+ userId);
    User loggedInUser = userService.getById(userId);
    System.out.println(loggedInUser);
    CreatePostRequest createPostRequest = mapper.map(createPostRequestDto, CreatePostRequest.class);
    Post createdPost = postService.createPost(loggedInUser, createPostRequest);
    PostDto createdPostDto = mapper.map(createdPost, PostDto.class);
    return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
}
}
