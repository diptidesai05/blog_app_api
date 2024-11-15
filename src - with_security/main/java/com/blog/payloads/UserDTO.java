package com.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	
	private int id;
	@NotEmpty
	@Size(min=4, message="Username is of 4 character")
	private String name;
	@Email(message="Email address is not valid")
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(min=4, max=8, message="Password must be min of 4 and max of 8")
	private String password;
	@NotEmpty
	private String about;

}
