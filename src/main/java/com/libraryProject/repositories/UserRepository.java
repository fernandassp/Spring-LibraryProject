package com.libraryProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/*
	 Cadastrar usuário (com hash de senha): user service - aqui tem o método save

	 */
	

	@Query("SELECT u from users u where u.email = :email and u.password = :password")
	public Optional<User> login(@Param("email") String email, @Param("password")String password);
	
	public Optional<User> findByEmail(String email); 
	
	public List<User> findAllByRole(UserRole role);
	
}
