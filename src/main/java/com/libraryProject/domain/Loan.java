package com.libraryProject.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.libraryProject.domain.enums.LoanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "loans")
public class Loan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	private LocalDateTime loanDate; // se der algum problema, mudo para date

	@Column(nullable = false, updatable = false)
	private LocalDateTime returnDate;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private LoanStatus status;

	// um user tem vários loans
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id") // na tabela Loan, o User é FK - user_id: nome da FK. JPA pega a PK da tabela users
	private User user;

	// um book já teve vários loans
	@ManyToOne(optional = false)
	@JoinColumn(name = "book_id")
	private Book book;

	// um empréstimo tem uma lista de históricos / estágios do loan
	@OneToMany(mappedBy = "loan")
	@Getter(onMethod = @__({@JsonIgnore}))
	private List<LoanHistory> history = new ArrayList<>();

	public Loan(Long id, LocalDateTime loanDate, LocalDateTime returnDate, LoanStatus status, User user, Book book) {
		this.id = id;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.status = status;
		this.user = user;
		this.book = book;
	}

	public void addHistory(LoanHistory historyItem) {
		historyItem.setLoan(this);
		this.history.add(historyItem);
	}
}
