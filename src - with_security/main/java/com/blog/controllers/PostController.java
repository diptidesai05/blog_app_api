package com.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstant;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.services.FileService;
import com.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("{project.image}")
	private String path;
	
	//POST create
    @PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
			
			System.out.println("In createpost method");
			System.out.println("postDTO "+postDTO);
			PostDTO createdPostDTO=this.postService.createPost(postDTO, userId, categoryId);
			return new ResponseEntity<PostDTO>(createdPostDTO,HttpStatus.CREATED);
			
	}
    
    //GET all posts by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
    	
    	return ResponseEntity.ok(this.postService.getPostsByCategory(categoryId));
    }

    //GET all posts by category
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
    	List<PostDTO> postList=this.postService.getPostsByUser(userId);
    	return new ResponseEntity<List<PostDTO>>(postList,HttpStatus.OK);
    }
    
    //GET all posts
    @GetMapping("/")
    ResponseEntity<PostResponse> getPosts(
    		@RequestParam(value="pageNumber", defaultValue=AppConstant.PAGE_NUMBER,required=false) Integer pageNumber,
    		@RequestParam(value="pageSize", defaultValue=AppConstant.PAGE_SIZE,required=false) Integer pageSize,
    		@RequestParam(value="sortBy", defaultValue=AppConstant.SORT_BY,required=false) String sortBy,
    		@RequestParam(value="sortDir", defaultValue=AppConstant.SORT_DIR,required=false) String sortDir
    		){
    	PostResponse postResponse=this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
    	return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }
    
    //GET single post
    @GetMapping("/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
    	
    	PostDTO postDTO=this.postService.getPostById(postId);
    	return new ResponseEntity<PostDTO>(postDTO,HttpStatus.OK);
    }
    
    //Delete single post
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){		
		this.postService.deletePost(postId);
		//return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
		//return new ResponseEntity(Map.of("message", "User deleted successfully"), HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
	}
    
    //PUT Update post
  	@PutMapping("/{postId}")
  	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("postId") Integer postId){
  		
  		PostDTO updatedPostDTO=this.postService.updatePost(postDTO, postId);
  		return ResponseEntity.ok(updatedPostDTO);
  	}
  	//GET search post containging keywords
  	@GetMapping("/search/{keywords}")
  	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable("keywords") String keywords){
  		List<PostDTO> postList=this.postService.getPostByKeyword(keywords);
  		return new ResponseEntity<List<PostDTO>>(postList,HttpStatus.OK);
  	}
  	//POST image upload
  	@PostMapping("/image/upload/{postId}")
  	public ResponseEntity<PostDTO> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable("postId") Integer postId) throws IOException{
  		 PostDTO postDTO=this.postService.getPostById(postId); 
  		 String fileName=this.fileService.uploadImage(path, image);
  		 postDTO.setImageName(fileName);
  		 PostDTO updatedPostDTO=this.postService.updatePost(postDTO, postId);
  		return ResponseEntity.ok(updatedPostDTO);
  	}
  	
    //method to serve files
  	@GetMapping(value="/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
  	public void downloadImage(
  			@PathVariable("imageName") String imageName,
  			HttpServletResponse response
  			) throws IOException {
  		InputStream resource=this.fileService.getResource(path, imageName);
  		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
  		StreamUtils.copy(resource, response.getOutputStream());
  	}
}
