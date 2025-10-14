package com.libraryProject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.libraryProject.domain.LoanHistory;
@Repository
public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long>{

	// ÚTIL - Buscar por status específico
	// public List<LoanHistory> findAllByLoanIdAndStatus(Long loanId, LoanStatus status);
	
	public List<LoanHistory> findAllByLoanId(Long id); 
}
