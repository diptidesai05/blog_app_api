package com.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	//This also works
	//List<Post> findByTitleContaining(String title);
	//another solution
	@Query("select p from Post p where p.title like :key")
	List<Post> findByTitleContaining(@Param("key") String title);
}