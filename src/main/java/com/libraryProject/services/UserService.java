package com.libraryProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;
import com.libraryProject.exception.NotFoundException;
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
		return result.orElseThrow( () -> new NotFoundException("Não existe usuário com id = " + id) );
	}
	
	public List<User> listAll(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public User login(String email, String password) {
		
		password = HashUtil.getSecureHash(password);
		
		Optional<User> result = userRepository.login(email, password);
		return result.orElseThrow(() -> new NotFoundException("E-mail ou senha incorretos."));
	}
	
    public User findByEmail(String email) {
    	Optional<User> result = userRepository.findByEmail(email);
        return result.orElseThrow(() -> new NotFoundException("Não existe usuário com e-mail = " + email));
    }
    
    public List<User> findByRole(UserRole role) {
        return userRepository.findAllByRole(role);
    }
    
    public int updateRole(User user) {
    	return userRepository.updateRole(user.getId(), user.getRole()); // setar o role primeiro
    }
}
