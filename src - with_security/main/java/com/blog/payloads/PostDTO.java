package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
public class PostDTO {
	

	private Integer postId;
	@NotEmpty
	@Size(min=4, message="post title should be min of 4 character")
	private String title;
	@NotEmpty
	@Size(min=4, message="post content should be min of  4 character")
	private String content;
	private String imageName;
	private Date creationDate;
	private CategoryDTO category;
	private UserDTO user;
	private Set<CommentDTO> comments=new HashSet<>(); 

}
