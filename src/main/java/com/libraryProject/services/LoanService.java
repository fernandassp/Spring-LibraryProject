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

	public Loan requestLoan(Long userId, Long bookId) {

		User user = userService.getById(userId);
		Book book = bookService.getById(bookId);

		// validações: se livro está disponível || usuario existe || livro existe


		Loan loan = new Loan();
		loan.setUser(user);
		loan.setBook(book);
		loan.setStatus(LoanStatus.REQUESTED);
		loan.setLoanDate(LocalDateTime.now());
		loan.setReturnDate(LocalDateTime.now().plusDays(15));
		Loan savedLoan = loanRepository.save(loan);
		createInitialLoanHistory(savedLoan);

		return savedLoan;
	}
	
	public Loan approveLoan(Long loanId) {
		
		// validações: apenas LIBRARIAN/ADMIN (user role) || loan tem que ser requested
		
		Loan loan = getById(loanId);
		
		loan.setStatus(LoanStatus.APPROVED);
		Loan updatedLoan = update(loan);
		
		Book book = loan.getBook();
		book.setAvailable(false);
		bookService.updateAvailability(book);
		
		createNewLoanHistory(updatedLoan, LoanStatus.APPROVED, "Empréstimo aprovado pela biblioteca");
		return updatedLoan;
	}
	
	public Loan registerWithdrawal(Long loanId) { // retirada do livro
		// validações: apenas LIBRARIAN/ADMIN (user role) || loan tem que ser approved
		Loan loan = getById(loanId);
		
		loan.setStatus(LoanStatus.IN_PROGRESS);
		Loan updatedLoan = update(loan);
		 
		createNewLoanHistory(updatedLoan, LoanStatus.IN_PROGRESS, "Livro retirado pelo usuário " + loan.getUser().getName());
		return updatedLoan;
	}
	
	public Loan registerBookReturn(Long loanId) {
		// validações: apenas LIBRARIAN/ADMIN (user role) || loan tem que ser in progress
		Loan loan = getById(loanId);
		loan.setStatus(LoanStatus.RETURNED);
		Loan updatedLoan = update(loan);
		
		createNewLoanHistory(updatedLoan, LoanStatus.RETURNED, "Livro devolvido pelo usuário " + loan.getUser().getName());
		
		Book book = loan.getBook();
		book.setAvailable(true);
		bookService.updateAvailability(book); // liberar livro
		
		return updatedLoan;
	}

	private void createNewLoanHistory(Loan loan, LoanStatus status, String description) {
		LoanHistory history = new LoanHistory();
	    history.setRealDate(LocalDateTime.now());
	    history.setStatus(status);
	    history.setDescription(description);
	    history.setLoan(loan);
	    loanHistoryRepository.save(history);
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
	
	/*
	public int updateStatus(Loan loan) {
		return loanRepository.updateStatus(loan.getId(), loan.getStatus()); // setar primeiro
	}
	*/

	public Loan getById(Long id) {
		return loanRepository.findById(id).get(); // botar AQUI a validação de not found ?
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
