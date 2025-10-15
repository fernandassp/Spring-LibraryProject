package com.libraryProject.dto;

import com.libraryProject.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdatedto {
	
	
	@NotBlank(message = "Name required")
	private String name;
	
	@Email(message = "Invalid email address")
	private String email;
	
	@NotBlank(message = "Password required")
	@Size(min = 5, max = 99, message = "Password length must be between 5 and 99")
	private String password;
	
	
	public void transformToUser(User existingUser) {
		existingUser.setEmail(email);
		existingUser.setName(name);
		existingUser.setPassword(password);
	}
}
