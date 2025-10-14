package com.libraryProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryProject.domain.Loan;
import com.libraryProject.domain.enums.LoanStatus;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
		
	public List<Loan> findAllByUserId(Long id);
	
	public List<Loan> findAllByStatus(LoanStatus status);
	
	/*
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE loans l SET l.status = :status WHERE l.id = :id")
	public int updateStatus(@Param("id") Long id, @Param("status") LoanStatus status);
	*/
	
	public List<Loan> findAllByBookId(Long id);
}
