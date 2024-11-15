package com.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blog.repo.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
    @Test
	public void repoTest() {
		
		//Using Reflection API
		String className=this.userRepo.getClass().getName();
		String packName=this.userRepo.getClass().getPackageName();
		System.out.println("className "+className);
		System.out.println("package "+packName);
		
	}
}
