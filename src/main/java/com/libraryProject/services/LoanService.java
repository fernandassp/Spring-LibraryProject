package com.libraryProject.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.Book;
import com.libraryProject.domain.Loan;
import com.libraryProject.domain.LoanHistory;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.LoanStatus;
import com.libraryProject.repositories.LoanHistoryRepository;
import com.libraryProject.repositories.LoanRepository;

@Service
public class LoanService {

	@Autowired LoanRepository loanRepository;
	@Autowired LoanHistoryRepository loanHistoryRepository;
	@Autowired BookService bookService;
	@Autowired UserService userService;

	/*
	 * Aprovar empréstimo (status: APPROVED) - apenas LIBRARIAN/ADMIN (update) 
	 * Registrar retirada (status: IN_PROGRESS) (update) 
	 * Registrar devolução (status: RETURNED) (update)
	 */

	// versão 1
	public Loan requestLoan(Loan loan) {

		// validações

		loan.setStatus(LoanStatus.REQUESTED);
		loan.setLoanDate(LocalDateTime.now());
		loan.setReturnDate(LocalDateTime.now().plusDays(15));

		Loan savedLoan = loanRepository.save(loan);

		createInitialLoanHistory(savedLoan);

		return savedLoan;
	}

	// versão 2
	public Loan requestLoan(Long userId, Long bookId) {

		User user = userService.getById(userId);
		Book book = bookService.getById(bookId);

		// validações


		Loan loan = new Loan();
		loan.setUser(user);
		loan.setBook(book);
		loan.setStatus(LoanStatus.REQUESTED);
		loan.setLoanDate(LocalDateTime.now());
		loan.setReturnDate(LocalDateTime.now().plusDays(15));
		Loan savedLoan = loanRepository.save(loan);
		createInitialLoanHistory(savedLoan);

		book.setAvailable(false);
		bookService.updateAvailability(book);

		return savedLoan;
	}

	private void createInitialLoanHistory(Loan loan) {
		String description = String.format("Solicitação de empréstimo do livro %s para usuário %s",
				loan.getBook().getTitle(), loan.getUser().getName());
		LoanHistory historyItem = new LoanHistory();
		historyItem.setRealDate(LocalDateTime.now());
		historyItem.setDescription(description);
		historyItem.setStatus(loan.getStatus());
		historyItem.setLoan(loan);
		loanHistoryRepository.save(historyItem);
		loan.addHistory(historyItem);
	}
	
	public Loan update(Loan loan) {
		return loanRepository.save(loan);
	}

	public int updateStatus(Loan loan) {
		return loanRepository.updateStatus(loan.getId(), loan.getStatus()); // setar primeiro
	}

	public Loan getById(Long id) {
		return loanRepository.findById(id).get();
	}

	public List<Loan> listAll() {
		return loanRepository.findAll();
	}

	public List<Loan> listAllByUserId(Long id) {
		return loanRepository.findAllByUserId(id);
	}

	public List<Loan> listAllByStatus(LoanStatus status) {
		return loanRepository.findAllByStatus(status);
	}
}
