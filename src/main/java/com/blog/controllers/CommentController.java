package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDTO;
import com.blog.payloads.PostDTO;
import com.blog.services.CommentService;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin("*")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	private PostService postService;
	
	//POST Create Comment
	@PostMapping("/post/{postID}")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postID){
		
		CommentDTO createdcommentDTO=this.commentService.createComment(commentDTO, postID);
	    return new 	 ResponseEntity<CommentDTO>(createdcommentDTO, HttpStatus.CREATED); 
	}

	@DeleteMapping("/commentID")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("commentId") Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true), HttpStatus.OK);
	}
}
