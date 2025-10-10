/*
package com.libraryProject.repositoryTests;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;
import com.libraryProject.repositories.UserRepository;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired private UserRepository userRepository;
	
	@Test
	public void AsaveTest() {
		User user = new User(null, "Megan", "megan@gmail.com", "megan", UserRole.MEMBER, null);
		User saved = userRepository.save(user);
		
		assertThat(saved.getId()).isEqualTo(1L);
	}
	@Test
	public void updateTest() {
		User user = new User(1L, "Megan", "megankatseye@gmail.com", "megan", UserRole.MEMBER, null);
		User updatedUser = userRepository.save(user); // quando passa o ID, faz um update do existente
		
		assertThat(updatedUser.getEmail()).isEqualTo("megankatseye@gmail.com");
	}
	@Test
	public void BloginTest() {
		User user = new User(null, "Daniela", "daniela@gmail.com", "daniela", UserRole.MEMBER, null);
		userRepository.save(user);
		Optional<User> result = userRepository.login("daniela@gmail.com", "daniela");
		assertThat(result.get().getId()).isEqualTo(2L);
	}
	
	@Test
	public void listAllTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(2);
	}
	
	@Test
	public void getByEmailTest() {
		Optional<User> result = userRepository.findByEmail("daniela@gmail.com");
		assertThat(result.get().getId()).isEqualTo(2L);
	}

	@Test
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		assertThat(result.get().getName()).isEqualTo("Megan");
	}
	
	@Test
	public void getByRoleTest() {
		List<User> result = userRepository.findAllByRole(UserRole.MEMBER);
		assertThat(result.size()).isEqualTo(2);
	}
}

*/

package com.libraryProject.repositoryTests;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.UserRole;
import com.libraryProject.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {

    @Autowired 
    private UserRepository userRepository;

    @Test
    @Order(1) 
    void saveTest() {
    	User user = new User(null, "Lara", "lara@gmail.com", "lara", UserRole.MEMBER, null);
		User saved = userRepository.save(user);
		
		assertThat(saved.getId()).isEqualTo(5L);
    }

    @Test
    @Order(2)   
    void updateTest() {
    	User user = new User(5L, "Lara", "larakatseye@gmail.com", "lara", UserRole.MEMBER, null);
		User updatedUser = userRepository.save(user); // quando passa o ID, faz um update do existente
		
		assertThat(updatedUser.getEmail()).isEqualTo("larakatseye@gmail.com");
    }

    @Test
    @Order(3) 
    void loginTest() {
    	User user = new User(null, "Luna", "luna@gmail.com", "luna", UserRole.MEMBER, null);
		userRepository.save(user);
		Optional<User> result = userRepository.login("luna@gmail.com", "luna");
		assertThat(result.get().getId()).isEqualTo(6L);
    }

    @Test
    @Order(4)
    void listAllTest() {
    	List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(4);
    }

    @Test
    @Order(5)
    void getByEmailTest() {
    	Optional<User> result = userRepository.findByEmail("daniela@gmail.com");
		assertThat(result.get().getId()).isEqualTo(4L);
    }

    @Test
    @Order(6)
    void getByIdTest() {
    	Optional<User> result = userRepository.findById(3L);
		assertThat(result.get().getName()).isEqualTo("Megan");
    }

    @Test
    @Order(7)
    void getByRoleTest() {
    	List<User> result = userRepository.findAllByRole(UserRole.MEMBER);
		assertThat(result.size()).isEqualTo(4);
    }
}

