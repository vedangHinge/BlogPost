package com.cdac.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cdac.entity.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID>{
	
	@Query("SELECT c from Category c  LEFT JOIN FETCH c.posts")
	List<Category> findAllCategoryWithPosts();
	
	Boolean existsByName(String name);
	Optional<Category> findByName(String name);
}
