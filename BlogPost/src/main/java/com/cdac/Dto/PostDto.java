package com.cdac.Dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.cdac.entity.PostStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostDto {
private UUID id;
private String title;
private String content;
private AuthorDto myUser;
private CategoryDto myCategory;
private Set<TagDto> tags;
private Integer readingTime;
private LocalDateTime createdOn;
private LocalDateTime updatedOn;
private PostStatus status;
}
