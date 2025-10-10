package com.libraryProject.domain;
import java.time.LocalDateTime;
import com.libraryProject.domain.enums.LoanStatus;

public class Loan {

	private Long id;
	private LocalDateTime loanDate; // se der algum problema, mudo para date
	private LocalDateTime returnDate;
	private LoanStatus status;
	
	private User user;
	private Book book;
}
