package com.cdac.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.Dto.PostDto;
import com.cdac.custom_exception.ResourceNotFoundException;
import com.cdac.entity.Category;
import com.cdac.entity.CreatePostRequest;
import com.cdac.entity.Post;
import com.cdac.entity.PostStatus;
import com.cdac.entity.Tag;
import com.cdac.entity.User;
import com.cdac.repo.CategoryRepo;
import com.cdac.repo.PostRepo;
import com.cdac.repo.TagsRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{
	private CategoryRepo catRepo;
	private TagsRepo tagRepo;
	private PostRepo postRepo;
	private ModelMapper mapper;
	private TagService tagService;
	private CategoryService categoryService;
	 private static final int WORDS_PER_MINUTE = 200;
	@Override
	public List<PostDto> getAllPosts(UUID categoryId, UUID tagId) {
		if(categoryId!=null && tagId!=null) {
			Category category=catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
			Tag tag= tagRepo.findById(tagId).orElseThrow(()->new ResourceNotFoundException("Tag not found"));
			List<Post> posts=postRepo.findAllByStatusAndMyCategoryAndTagsContaining(PostStatus.PUBLISHED, category, tag);
			return posts.stream().map(a->mapper.map(a, PostDto.class)).collect(Collectors.toList());
		}
		if(categoryId!=null) {
			Category category=catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
			List<Post> posts=postRepo.findAllByStatusAndMyCategory(PostStatus.PUBLISHED, category);
			return posts.stream().map(a->mapper.map(a, PostDto.class)).collect(Collectors.toList());
		}
		if(tagId!=null) {
		
			Tag tag= tagRepo.findById(tagId).orElseThrow(()->new ResourceNotFoundException("Tag not found"));
			List<Post> posts=postRepo.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED,tag);
			return posts.stream().map(a->mapper.map(a, PostDto.class)).collect(Collectors.toList());
		}
		 List<Post> list= postRepo.findAllByStatus(PostStatus.PUBLISHED);
		 return list.stream().map(a->mapper.map(a, PostDto.class)).collect(Collectors.toList());
	}
	@Override
	public List<PostDto> getDraftPosts(User user) {
		List<Post> list=postRepo.findAllByMyUserAndStatus(user, PostStatus.DRAFT);
		
		return list.stream().map(a->mapper.map(a, PostDto.class)).collect(Collectors.toList());
	}
	 private Integer calculateReadingTime(String content) {
	        if (content == null || content.isEmpty()) {
	            return 0;
	        }
	        int wordCount = content.trim().split("\\s+").length;
	        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
	    }
	@Override
	@Transactional
	public Post createPost(User user, CreatePostRequest createPostRequest) {
		Post newPost=new Post();
		newPost.setTitle(createPostRequest.getTitle());
		newPost.setContent(createPostRequest.getContent());
		newPost.setStatus(createPostRequest.getStatus());
		newPost.setMyUser(user);
		newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));
		Category category=categoryService.getCategoryById(createPostRequest.getCategoryId());
		newPost.setMyCategory(category);
		Set<UUID> tagIds=createPostRequest.getTagIds();
		List<Tag> tags=tagService.getTagsById(tagIds);
		newPost.setTags(new HashSet<>(tags));
		
		return postRepo.save(newPost);
		
	}

}
