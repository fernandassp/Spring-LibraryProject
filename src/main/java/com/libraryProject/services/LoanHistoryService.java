package com.libraryProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.LoanHistory;
import com.libraryProject.repositories.LoanHistoryRepository;

@Service
public class LoanHistoryService {  // por enquanto, não parece ser útil

	@Autowired LoanHistoryRepository loanHistoryRepository;

	public LoanHistory save(LoanHistory history) {
		return loanHistoryRepository.save(history);
	}
	
	public LoanHistory update(LoanHistory history) {
		return save(history);
	}
	
	public List<LoanHistory> listAll(){
		return loanHistoryRepository.findAll();
	}
	
	public LoanHistory getById(Long id) {
		return loanHistoryRepository.findById(id).get();
	}
	
	public List<LoanHistory> listAllByLoanId(Long loanId){
		return loanHistoryRepository.findAllByLoanId(loanId);
	}
}

