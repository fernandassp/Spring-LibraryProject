package com.libraryProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.User;
import com.libraryProject.repositories.UserRepository;

@Service
public class UserService {

	@Autowired UserRepository userRepository;
	
	/*
	 Cadastrar usuário com hash de senha
	 save - update - get - list - login 
	 */
	
	public User save(User user) {
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	public User update(User user) {
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result.get();
	}
	
	public List<User> listAll(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public User login(String email, String password) {
		Optional<User> result = userRepository.login(email, password);
		return result.get();
	}
}
