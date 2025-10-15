package com.libraryProject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryProject.domain.LoanHistory;
import com.libraryProject.services.LoanHistoryService;

@RestController
@RequestMapping(value = "loanHistory")
public class LoanHistoryResource {
	
	@Autowired LoanHistoryService loanHistoryService;	
	
	@GetMapping("/loan/{loanId}")
	public ResponseEntity<List<LoanHistory>> listAllByLoanId(@PathVariable(name = "loanId") Long loanId){
		List<LoanHistory> history = loanHistoryService.listAllByLoanId(loanId);
		return ResponseEntity.ok(history);
	}
}
