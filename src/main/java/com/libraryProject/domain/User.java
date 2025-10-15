package com.libraryProject.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.libraryProject.domain.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users") // nome usado no JPQL
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)  
	private String name;
	
	@Column (nullable = false, unique = true)
	private String email;
	
	
	@Column(nullable = false, length = 75)
	@Getter(onMethod = @__({@JsonIgnore}))
	@Setter(onMethod = @__({@JsonProperty}))
	private String password;
	
	@Column(nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	
	// um user tem uma lista de empréstimos
	@OneToMany(mappedBy = "user") // NOME DO ATRIBUTO USER NA CLASSE LOAN
	@Getter(onMethod = @__({@JsonIgnore}))
	private List<Loan> loans = new ArrayList<>();
	
	
	public void addLoan(Loan loan) {
		this.loans.add(loan);
		loan.setUser(this);
	}
	
	//@Table(name = "nome_tb") se quiser mudar nome da tabela no banco

	// -------- ** depois pensar em valores padrão para os atributos das entidades
}
