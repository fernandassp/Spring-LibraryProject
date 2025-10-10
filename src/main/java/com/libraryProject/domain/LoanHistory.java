package com.libraryProject.domain;

import java.time.LocalDateTime;
import jakarta.persistence.Id; 
import com.libraryProject.domain.enums.LoanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
@Entity(name = "loanHistory")
public class LoanHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime realDate;
	
	@Column(nullable = false, columnDefinition = "text")
	private String description;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private LoanStatus status;
	
	@ManyToOne
	@JoinColumn(name = "loan_id")
	private Loan loan;
}
