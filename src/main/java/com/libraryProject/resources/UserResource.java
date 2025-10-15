package com.libraryProject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;
import com.libraryProject.dto.UserLogindto;
import com.libraryProject.dto.UserUpdateRoledto;
import com.libraryProject.model.PageModel;
import com.libraryProject.model.PageRequestModel;
import com.libraryProject.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired UserService userService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user){
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	} 
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value="page") int page, 
			@RequestParam(value="size") int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login (@RequestBody UserLogindto userDto){
		User loggedUser = userService.login(userDto.getEmail(), userDto.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@PatchMapping("/{id}/role")
	public ResponseEntity<User> updateRole(@PathVariable(name="id") Long id, @RequestBody UserUpdateRoledto role){
		User user = new User();
		user.setId(id);
		user.setRole(role.getRole());
		userService.updateRole(user);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<User>> getByRole(@PathVariable UserRole role){
		List<User> users = userService.findByRole(role);
		return ResponseEntity.ok(users);
	}
	
	// listar todos empréstimos de um usuário?
}
