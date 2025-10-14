package com.libraryProject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libraryProject.domain.Book;
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
		return bookRepository.findById(id).get();
	}
	
	public List<Book> listAll(){
		return bookRepository.findAll();
	}
	
	public int updateAvailability(Book book) {
		return bookRepository.updateAvailability(book.getAvailable(), book.getId()); // setar o av primeiro
	}
	
	public List<Book> listAllAvailable(){
		return bookRepository.findAllAvailableBooks();
	}
	
	public List<Book> getByAuthor(String author){
		return bookRepository.findByAuthor(author);
	}
	
	public Book getByTitle(String title) {
		return bookRepository.findByTitle(title).get();
	}
	
	public List<Book> getByAvailable(Boolean available){
		return bookRepository.findByAvailable(available);
	}
}
