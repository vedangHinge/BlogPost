package com.cdac.service;



import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.Dto.ApiResponse;
import com.cdac.Dto.CategoryAddDto;
import com.cdac.Dto.CategoryDto;
import com.cdac.custom_exception.ResourceNotFoundException;
import com.cdac.entity.Category;
import com.cdac.repo.CategoryRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private CategoryRepo catRepo;
	private ModelMapper mapper;
	@Override
	public List<CategoryDto> listCategories() {
		List<Category>catList=catRepo.findAllCategoryWithPosts();
		if(catList.isEmpty()) {
			throw new ResourceNotFoundException("No such elements found");
		}
		return catList.stream()
				.map(l->{
					CategoryDto dto=mapper.map(l, CategoryDto.class);
					if(dto.getPostCounts()==null) {
						dto.setPostCounts(l.getPosts()==null? 0L:(long)l.getPosts().size());
					}
					return dto;
				}).collect(Collectors.toList());
		
	}
	@Override
	public ApiResponse addNewCategory(CategoryAddDto newCat) {
		if(catRepo.existsByName(newCat.getName())) {
			throw new ResourceNotFoundException("Category with same name already exists");
		}
		Category myCat=mapper.map(newCat, Category.class);
		catRepo.save(myCat);
		return new ApiResponse("Category Added successfully");
	}
	@Transactional
	@Override
	public void deleteCategory(UUID id) {
		Category cat=catRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category does not exists"));
		
		if(cat.getPosts().size()>0) {
			throw new RuntimeException("posts exists in this category");
		}
		catRepo.delete(cat);
		
	}
	@Override
	public Category getCategoryById(UUID id) {
		return catRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category not found"));
	}
	

}
