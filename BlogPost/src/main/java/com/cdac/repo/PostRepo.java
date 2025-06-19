package com.cdac.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.Category;
import com.cdac.entity.Post;
import com.cdac.entity.PostStatus;
import com.cdac.entity.Tag;
import com.cdac.entity.User;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {
 List<Post> findAllByStatusAndMyCategoryAndTagsContaining(PostStatus status,Category myCategory,Tag tag);

 List<Post> findAllByStatusAndMyCategory(PostStatus status,Category myCategory);

 List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);

 List<Post> findAllByStatus(PostStatus status);

 List<Post> findAllByMyUserAndStatus(User author, PostStatus status);
 
}
