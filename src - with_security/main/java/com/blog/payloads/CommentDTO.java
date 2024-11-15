package com.blog.payloads;

import com.blog.entities.Post;
import com.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO {
	
	private int id;
    private String content;
   // private PostDTO post;
   // private UserDTO user;
 
}
