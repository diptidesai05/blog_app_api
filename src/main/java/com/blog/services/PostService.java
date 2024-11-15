package com.blog.services;

import java.util.List;

import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;

public interface PostService {
	
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	PostDTO getPostById(Integer postId);
	//List<PostDTO> getAllPosts();
	//List<PostDTO> getAllPosts(Integer pageNumber, Integer pageSize);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	void deletePost(Integer postId);
	//get all post by category
	List<PostDTO> getPostsByCategory(Integer categoryId);
	//get all post by user
	List<PostDTO> getPostsByUser(Integer userId);
	//get or search posts by any keywords
	List<PostDTO> getPostByKeyword(String keyword);

}
