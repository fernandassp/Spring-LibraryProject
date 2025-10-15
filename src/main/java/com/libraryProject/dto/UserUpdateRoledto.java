package com.libraryProject.dto;

import com.libraryProject.domain.enums.UserRole;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoledto {

	@NotNull(message = "Role required")
	private UserRole role;
}
