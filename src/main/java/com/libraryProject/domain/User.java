package com.libraryProject.domain;

import com.libraryProject.domain.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private UserRole role;
}
