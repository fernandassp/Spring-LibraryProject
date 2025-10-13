package com.libraryProject.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.libraryProject.domain.Loan;
import com.libraryProject.domain.enums.LoanStatus;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	/*
	Solicitar empréstimo (status: REQUESTED)
	Aprovar empréstimo (status: APPROVED) - apenas LIBRARIAN/ADMIN
	Registrar retirada (status: IN_PROGRESS)
	Registrar devolução (status: RETURNED)
	
	 * */
	
	
	public List<Loan> findAllByUserId(Long id);
	
	public List<Loan> findAllByStatus(LoanStatus status);
	
	/*
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE loans l SET l.history = :history WHERE l.id = :id")
	public int updateHistory(@Param("id") Long id, @Param("history") List<LoanHistory> history);
	*/
}
