package com.libraryProject.repositoryTests;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.libraryProject.domain.Book;
import com.libraryProject.domain.Loan;
import com.libraryProject.domain.LoanHistory;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.LoanStatus;
import com.libraryProject.repositories.BookRepository;
import com.libraryProject.repositories.LoanHistoryRepository;
import com.libraryProject.repositories.LoanRepository;
import com.libraryProject.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanHistoryRepositoryTests {

	@Autowired LoanHistoryRepository loanHistoryRepository;
	@Autowired LoanRepository loanRepository;
	@Autowired UserRepository userRepository;
	@Autowired BookRepository bookRepository;
	
	@Test
	@Order(1)
	public void saveTest() {
		User user = userRepository.findById(4L).get();
		Book book = bookRepository.findById(2L).get();
		Loan loan = new Loan(null, LocalDateTime.now(), LocalDateTime.now().plusDays(15), 
				LoanStatus.REQUESTED, user, book, null); // novo loan(3) para user 4 e book 2
		Loan savedLoan = loanRepository.save(loan);
		
		LoanHistory loanHistory = new LoanHistory(null, LocalDateTime.now(), 
				"Requerimento de empréstimo do livro Stalker para usuário Daniela", LoanStatus.REQUESTED, savedLoan);
		LoanHistory savedLoanHistory = loanHistoryRepository.save(loanHistory);
		
		
		savedLoan.addHistory(loanHistory);
		loanRepository.save(savedLoan); // salvar dnv - atualizar
		
		
		assertThat(savedLoanHistory.getId()).isEqualTo(3L);
		
	}
	
	@Test
	@Order(2)
	public void getAllByLoanIdTest() {
		List<LoanHistory> history = loanHistoryRepository.findAllByLoanId(4L);
		assertThat(history.get(0).getId()).isEqualTo(3L);
	}
}
