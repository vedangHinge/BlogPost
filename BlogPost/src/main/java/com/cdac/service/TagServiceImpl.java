package com.cdac.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.Dto.ApiResponse;
import com.cdac.Dto.TagCreateReqDto;
import com.cdac.Dto.TagDto;
import com.cdac.custom_exception.ResourceNotFoundException;
import com.cdac.entity.Tag;
import com.cdac.repo.TagsRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService{
	
	

	

	private TagsRepo tagRepo;
	private ModelMapper mapper;
	@Transactional
	@Override
	public void deleteTag(UUID id) {
		tagRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No element with this id"));
		tagRepo.deleteById(id);
		
	}
	@Override
	public List<Tag> getTagsById(Set<UUID> tagIds) {
		List<Tag> foundTags=tagRepo.findAllById(tagIds);
		 if (foundTags.size() != tagIds.size()) {
	            throw new ResourceNotFoundException("Not found all the tags Specified");
	        }
	        return foundTags;

	}
	@Override
	public ApiResponse addNewTag(TagCreateReqDto tagDto) {
	    Set<String> tagNames = tagDto.getNames();

	    List<Tag> existingTags = tagRepo.findByNameIn(tagNames);
	    Set<String> existingNames = existingTags.stream()
	                                            .map(Tag::getName)
	                                            .collect(Collectors.toSet());

	    List<Tag> newTags = tagNames.stream()
	                                .filter(name -> !existingNames.contains(name))
	                                .map(name -> Tag.builder()
	                                                .name(name)
	                                                .posts(new HashSet<>())
	                                                .build())
	                                .toList();

	    if (!newTags.isEmpty()) {
	        tagRepo.saveAll(newTags);
	    }

	    return new ApiResponse("Tags saved successfully");
	}

	@Override
	public List<TagDto> getAllTags() {
		List<Tag> tags=new ArrayList<>();
		tags=tagRepo.findAllWithPostCounts();
		if(tags.isEmpty()) {
			throw new ResourceNotFoundException("No Tag found");
		}
		return tags.stream()
				.map(a->{
					TagDto tagDto=mapper.map(a, TagDto.class);
					if(tagDto.getPostCount()==null) {
						tagDto.setPostCount(a.getPosts()==null? 0:(Integer)a.getPosts().size());
					}
					return tagDto;
				}).collect(Collectors.toList());
	}

	

}
