package com.libraryProject.repositoryTests;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.libraryProject.domain.Book;
import com.libraryProject.repositories.BookRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookRepositoryTests {

	 @Autowired BookRepository bookRepository;
	 
	 @Test
	 @Order(1)
	 public void saveTest() {
		 Book book = new Book(null, "Stalker", "Lars Kepler", "9854216374501", true, null);
		 Book savedBook = bookRepository.save(book);
		 assertThat(savedBook.getId()).isEqualTo(2L);
	 }
	 
	 @Test
	 @Order(2)
	 public void getByIdTest() {
		 Optional<Book> result = bookRepository.findById(1L); 

		 assertThat(result.get().getTitle()).isEqualTo("Outsider");
	 }
	 
	 @Test
	 @Order(3)
	 public void updateAvailabilityTest() {
		 
		 int rows = bookRepository.updateAvailability(false, 1L);
		 assertThat(rows).isEqualTo(1);
		 
	 }
	 
	 @Test
	 @Order(4)
	 public void findAllAvailableBooksTest() {
		 
		 List<Book> availableBooks = bookRepository.findAllAvailableBooks();
		 assertThat(availableBooks.size()).isEqualTo(1);
		 
	 }
}
