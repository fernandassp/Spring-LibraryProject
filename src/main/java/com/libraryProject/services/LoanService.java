package com.libraryProject.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.Book;
import com.libraryProject.domain.Loan;
import com.libraryProject.domain.LoanHistory;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.LoanStatus;
import com.libraryProject.exception.NotFoundException;
import com.libraryProject.exception.UnavailableBookException;
import com.libraryProject.repositories.LoanHistoryRepository;
import com.libraryProject.repositories.LoanRepository;

@Service
public class LoanService {

	@Autowired LoanRepository loanRepository;
	@Autowired LoanHistoryRepository loanHistoryRepository;
	@Autowired BookService bookService;
	@Autowired UserService userService;

	public Loan requestLoan(Long userId, Long bookId) {

		// não pode fazer request de vários loans iguais (mesmo user e mesmo book, se já existir um loan assim diferente de returned)
		User user = userService.getById(userId);
		Book book = bookService.getById(bookId);
		
		if(!book.getAvailable()) {
			throw new UnavailableBookException(String.format("O livro %s não está disponível.", book.getTitle()));
		}
		
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
		
		// validações: apenas LIBRARIAN/ADMIN (user role) ?
		
		Loan loan = getById(loanId);
		if(loan.getStatus() != LoanStatus.REQUESTED) {
			throw new RuntimeException("O empréstimo deve estar no estado 'requerido' para poder ser aprovado.");
		}
		
		loan.setStatus(LoanStatus.APPROVED);
		Loan updatedLoan = update(loan);
		
		Book book = loan.getBook();
		bookService.updateAvailability(book.getId(), false);
		
		createNewLoanHistory(updatedLoan, LoanStatus.APPROVED, "Empréstimo aprovado pela biblioteca");
		return updatedLoan;
	}
	
	public Loan registerWithdrawal(Long loanId) { // retirada do livro
		// validações: apenas LIBRARIAN/ADMIN (user role) ? 
		Loan loan = getById(loanId);
		
		if(loan.getStatus() != LoanStatus.APPROVED) {
			throw new RuntimeException("O empréstimo deve estar no estado 'aprovado' para poder ser marcado como 'retirado'.");
		}
		
		loan.setStatus(LoanStatus.IN_PROGRESS);
		Loan updatedLoan = update(loan);
		 
		createNewLoanHistory(updatedLoan, LoanStatus.IN_PROGRESS, "Livro retirado pelo usuário " + loan.getUser().getName());
		return updatedLoan;
	}
	
	public Loan registerBookReturn(Long loanId) {
		// validações: apenas LIBRARIAN/ADMIN (user role) ?
		Loan loan = getById(loanId);
		if(loan.getStatus() != LoanStatus.IN_PROGRESS) {
			throw new RuntimeException("O empréstimo deve estar no estado 'em progresso' para poder ser marcado como 'devolvido'.");
		}
		
		loan.setStatus(LoanStatus.RETURNED);
		Loan updatedLoan = update(loan);
		
		createNewLoanHistory(updatedLoan, LoanStatus.RETURNED, "Livro devolvido pelo usuário " + loan.getUser().getName());
		
		Book book = loan.getBook();
		bookService.updateAvailability(book.getId(), true); // liberar livro
		
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
		Optional<Loan> result = loanRepository.findById(id);
		return result.orElseThrow( () -> new NotFoundException("Não existe empréstimo com id = " + id) );
	}

	public List<Loan> listAll() {
		return loanRepository.findAll();
	}

	public List<Loan> listAllByUserId(Long id) {
		return loanRepository.findAllByUserId(id);
	}

	
	public List<Loan> listAllByBookId(Long id) {
		return loanRepository.findAllByBookId(id);
	}
	
	public List<Loan> listAllByStatus(LoanStatus status) {
		return loanRepository.findAllByStatus(status);
	}
}
