package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
	 
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
    
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
		
	}

	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void run(String... args) throws Exception {
		//System.out.println(this.bCryptPasswordEncoder.encode("m"));
		
	}
}
