package com.cdac.Dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class TagCreateReqDto {
	@NotEmpty(message ="At least one tag name is required")
	 @Size(max = 10, message = "Maximum {max} tags allowed")
	
	private Set<@Size(min = 2, max = 30, message = "Tag name must be between {min} and {max} character")
	@Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters")
	String> names;
	
}
