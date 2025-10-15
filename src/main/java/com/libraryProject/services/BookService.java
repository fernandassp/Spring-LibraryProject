package com.libraryProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.Book;
import com.libraryProject.exception.NotFoundException;
import com.libraryProject.repositories.BookRepository;

@Service
public class BookService {

	@Autowired BookRepository bookRepository;
	
	public Book save(Book book) {
		book.setAvailable(true);
		Book createdBook = bookRepository.save(book);
		return createdBook;
	}
	
	public Book update(Book book) {
		Book updatedBook = bookRepository.save(book);
		return updatedBook;
	}
	
	public Book getById(Long id) {
		Optional<Book> result = bookRepository.findById(id);
		return result.orElseThrow( () -> new NotFoundException("Não existe livro com id = " + id) );
				
	}
	
	public List<Book> listAll(){
		return bookRepository.findAll();
	}
	
	public Book updateAvailability(Long bookId, Boolean available) {
	    // busca o livro existente para preservar outros dados
	    Book book = getById(bookId);
	    book.setAvailable(available);
	    return bookRepository.save(book);
	}
	
	public List<Book> listAllAvailable(){
		return bookRepository.findAllAvailableBooks();
	}
	
	public List<Book> getByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	public Book getByTitle(String title) {
		Optional<Book> result = bookRepository.findByTitle(title);
		return result.orElseThrow(() -> new NotFoundException("Não foi encontrado livro com o título = " + title));
	}
	
	public List<Book> getByAvailable(Boolean available){
		return bookRepository.findByAvailable(available);
	}
}
