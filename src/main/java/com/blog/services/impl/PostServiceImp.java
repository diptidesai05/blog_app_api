package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.repo.CategoryRepo;
import com.blog.repo.PostRepo;
import com.blog.repo.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImp implements PostService {
	
	@Autowired
	private PostRepo postRepo; 
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", " id ", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", " id ", categoryId));
		Post post=this.modelMapper.map(postDTO, Post.class);
		post.setImageName("default.png");
		post.setCreationDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost=this.postRepo.save(post);
		return this.modelMapper.map(savedPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", " id ",postId));
		//post.setCategory(this.modelMapper.map(postDTO.getCategory(), Category.class));
		post.setContent(postDTO.getContent());
		//post.setCreationDate(postDTO.getCreationDate());
		post.setImageName(postDTO.getImageName());
		//post.setPostId(postDTO.getPostId());
		post.setTitle(postDTO.getTitle());
		//post.setUser(this.modelMapper.map(postDTO.getUser(), User.class));
		Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", " id ",postId));;
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	//public List<PostDTO> getAllPosts() {
	public PostResponse  getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")){
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		//Pageable p=PageRequest.of(pageNumber, pageSize);
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> postList=pagePost.getContent();
		//List<Post> postList=this.postRepo.findAll();
		List<PostDTO> postDTOList=postList.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		PostResponse postResponse =new PostResponse();
		postResponse.setContent(postDTOList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	//TODO implement pagination for getPostsByCategory
	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) {
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", " id ", categoryId));
		List<Post> postList=this.postRepo.findByCategory(category);
		List<PostDTO> postDTOList=postList.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOList;
	}

	//TODO implement pagination for getPostsByUser
	@Override
	public List<PostDTO> getPostsByUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", " id ", userId));
		List<Post> postList=this.postRepo.findByUser(user);
		List<PostDTO> postDTOList=postList.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOList;
	}

	@Override
	public List<PostDTO> getPostByKeyword(String keyword) {
		//This also works
		//List<Post> postList=this.postRepo.findByTitleContaining(keyword);
		//another solution
		List<Post> postList=this.postRepo.findByTitleContaining("%"+keyword+"%");
		List<PostDTO> postDTOList=postList.stream().map(post -> this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOList;
	}

	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", " id ",postId));;
		this.postRepo.delete(post);
	}
	
	

}
