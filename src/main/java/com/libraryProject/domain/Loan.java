package com.libraryProject.domain;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import com.libraryProject.domain.enums.LoanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity(name="loans")
public class Loan {

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
	@ManyToOne
	@JoinColumn(name="user_id") // na tabela Loan, o User é FK - user_id: nome da FK. JPA pega a PK da tabela users
	private User user; 
	
	// um book já teve vários loans
	@ManyToOne 
	@JoinColumn(name="book_id") 
	private Book book;
	
	
	// um empréstimo tem uma lista de históricos / estágios do loan
	@OneToMany(mappedBy = "loan")
	private List<LoanHistory> history = new ArrayList<>();
}
 