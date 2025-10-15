package com.libraryProject.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libraryProject.domain.LoanHistory;
import com.libraryProject.exception.NotFoundException;
import com.libraryProject.repositories.LoanHistoryRepository;

@Service
public class LoanHistoryService {  

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
		Optional<LoanHistory> result = loanHistoryRepository.findById(id);
		return result.orElseThrow( () -> new NotFoundException("Não existe histórico de empréstimo com id = " + id) );
	}
	
	public List<LoanHistory> listAllByLoanId(Long loanId){   // útil
		return loanHistoryRepository.findAllByLoanId(loanId);
	}
}

