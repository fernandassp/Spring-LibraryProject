package com.libraryProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;
import com.libraryProject.repositories.UserRepository;
import com.spring_course1.service.util.HashUtil;

@Service
public class UserService {

	@Autowired UserRepository userRepository;
	
	public User save(User user) {
		
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	public User update(User user) {
		
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
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
		
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(email, password);
		return result.get(); 
	}
	
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
    
    public List<User> findByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }
    
    public int updateRole(User user) {
    	return userRepository.updateRole(user.getId(), user.getRole()); // setar o role primeiro
    }
}
