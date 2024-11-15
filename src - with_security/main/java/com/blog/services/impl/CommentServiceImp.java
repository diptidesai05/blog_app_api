package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CommentDTO;
import com.blog.repo.CommentRepo;
import com.blog.repo.PostRepo;
import com.blog.repo.UserRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImp implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
    @Autowired
	private CommentRepo commentRepo;
    @Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", " id ",postId));
		Comment comment=this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		comment.setUser(post.getUser());
		Comment createdComment=this.commentRepo.save(comment);
		return this.modelMapper.map(createdComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
	Comment comment=this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", " id ",commentId));
		this.commentRepo.delete(comment);
	}

}
