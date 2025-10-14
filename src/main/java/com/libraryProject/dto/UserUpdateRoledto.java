package com.libraryProject.dto;

import com.libraryProject.domain.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoledto {

	private UserRole role;
}
