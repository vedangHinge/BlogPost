package com.cdac.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {
private String msg;
public ApiResponse(String msg) {
	this.msg=msg;
}
}
