package com.libraryProject.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity(name="books")
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false, unique = true)
	private String isbn;
	
	@Column(nullable = false)
	private Boolean available; // depois ver a questão do valor padrão true
	
	
	// um livro é emprestado várias vezes - vários loans
	@OneToMany(mappedBy = "book")
	@Getter(onMethod = @__({@JsonIgnore}))
	private List<Loan> loans = new ArrayList<>();
	
	
	public Book(Long id, String title, String author, String isbn, Boolean available, List<Loan> loans) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.available = available;
		this.loans = (loans != null) ? loans : new ArrayList<>(); // não dar erro para adicionar novo loan
	}

	public void addLoan(Loan loan) {
		loan.setBook(this);
		this.loans.add(loan);
	}
}
