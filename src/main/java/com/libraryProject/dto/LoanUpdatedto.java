package com.libraryProject.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.libraryProject.domain.Loan;

import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoanUpdatedto {

	@Future(message = "Return date must be in the future")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime returnDate;
	
	
	public void transformToLoan(Loan existingLoan) {
		
		existingLoan.setReturnDate(returnDate);
	}
	
}
