package com.libraryProject.domain;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Id;  // ✅ CORRETO para JPA!
import com.libraryProject.domain.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

//@Table(name = "nome_tb") se quiser mudar nome da tabela no banco

// -------- ** depois pensar em valores padrão para os atributos das entidades

@Entity(name = "users") // nome usado no JPQL
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name = "name", nullable = false)  // n precisa do name mas só pra treinar
	private String name;
	
	@Column (name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false, length = 75)
	private String password;
	
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	
	// um user tem uma lista de empréstimos
	@OneToMany(mappedBy = "user") // NOME DO ATRIBUTO USER NA CLASSE LOAN
	private List<Loan> loans = new ArrayList<>();
}
