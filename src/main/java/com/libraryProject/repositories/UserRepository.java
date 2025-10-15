package com.libraryProject.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u from users u where u.email = :email and u.password = :password")
	public Optional<User> login(@Param("email") String email, @Param("password")String password);
	
	public Optional<User> findByEmail(String email); 
	
	public List<User> findAllByRole(UserRole role);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE users u set u.role = :role where u.id = :id")
	public int updateRole(@Param("id")Long id, @Param("role")UserRole role);
	
	
	public Boolean existsByEmail(String email);
}
