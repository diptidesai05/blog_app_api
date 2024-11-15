package com.blog.services;

import com.blog.payloads.CommentDTO;

public interface CommentService {
	
	CommentDTO createComment(CommentDTO comment, Integer postId);
	void deleteComment(Integer commentId);

}
