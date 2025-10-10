package com.libraryProject.domain;

import java.time.LocalDateTime;

import com.libraryProject.domain.enums.LoanStatus;

public class LoanHistory {

	private Long id;
	private LocalDateTime realDate;
	private String description;
	private LoanStatus status;
	
	private Loan loan;
}
