package com.libraryProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserLogindto {

	// validações dos dados
	
	@Email(message = "Invalid e-mail address")
	private String email;
	
	@NotBlank(message = "Password required")
	private String password;
	
	
	
}
