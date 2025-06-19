package com.cdac.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.cdac.Dto.ApiResponse;
import com.cdac.Dto.TagCreateReqDto;
import com.cdac.Dto.TagDto;
import com.cdac.entity.Tag;


public interface TagService {
List<TagDto> getAllTags();

ApiResponse addNewTag( TagCreateReqDto tagDto);

void deleteTag(UUID id);

List<Tag> getTagsById(Set<UUID> tagIds);
}
