package com.blog.payloads;

import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {
	
	
	private Integer categoryId;
	@NotEmpty
	@Size(min=4, message="category name is of 4 character")
	private String categoryTitle;
	@Size(min=20, message="category name is of 20 character")
	private String categoryDes;

}
