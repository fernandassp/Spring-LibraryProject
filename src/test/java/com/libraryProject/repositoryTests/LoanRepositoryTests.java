package com.libraryProject.repositoryTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.libraryProject.domain.Book;
import com.libraryProject.domain.Loan;
import com.libraryProject.domain.User;
import com.libraryProject.domain.enums.LoanStatus;
import com.libraryProject.repositories.BookRepository;
import com.libraryProject.repositories.LoanRepository;
import com.libraryProject.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanRepositoryTests {

   @Autowired LoanRepository loanRepository;
   @Autowired BookRepository bookRepository;
   @Autowired UserRepository userRepository;
   
   @Test
   @Order(1)
   public void saveTest() {
	   Book book = new Book(null, "Outsider", "Stephen King", "isbn",  true, null);
	   bookRepository.save(book);
	   User user = userRepository.findById(3L).get();
	   Loan loan = new Loan(null, LocalDateTime.now(), LocalDateTime.now().plusDays(15), LoanStatus.REQUESTED, 
			   user, book, null);
	   
	   Loan savedLoan = loanRepository.save(loan);
	   
	   assertThat(savedLoan.getId()).isEqualTo(1L);
   }
   
   @Test
   @Order(2)
   public void listTest() {
	  List<Loan> loans = loanRepository.findAll();
	  assertThat(loans.size()).isEqualTo(1);
   }
   
   @Test
   @Order(3)
   public void getByIdTest() {
	   Optional<Loan> result = loanRepository.findById(1L);
	   assertThat(result.get().getStatus()).isEqualTo(LoanStatus.REQUESTED);
   }
   
   @Test
   @Order(4)
   public void getAllByUserIdTest() {
	  List<Loan> loans = loanRepository.findAllByUserId(3L);

	  assertThat(loans.size()).isEqualTo(1);
   }
   
   @Test
   @Order(5)
   public void getAllByStatusTest() {
		  List<Loan> loans = loanRepository.findAllByStatus(LoanStatus.REQUESTED);

		  assertThat(loans.size()).isEqualTo(1);
   }
   
 
   @Test
   public void updateHistoryTest() {
	   
   }
   
}

