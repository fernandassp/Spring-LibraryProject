package com.libraryProject.dto;

import java.util.ArrayList;
import java.util.List;

import com.libraryProject.domain.Loan;
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
	
	private List<Loan> loans = new ArrayList<>();
	
	public User transformToUser() {
		User user = new User(null, name, email, password, null, loans);
		return user;
	}
}
