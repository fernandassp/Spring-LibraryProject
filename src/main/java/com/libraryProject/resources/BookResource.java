package com.libraryProject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryProject.domain.Book;
import com.libraryProject.dto.BookSavedto;
import com.libraryProject.dto.BookUpdatedto;
import com.libraryProject.model.PageModel;
import com.libraryProject.model.PageRequestModel;
import com.libraryProject.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "books")
public class BookResource {

	@Autowired BookService bookService;
	
	@PostMapping
	public ResponseEntity<Book> save(@RequestBody @Valid BookSavedto bookdto){
		Book createdBook = bookService.save(bookdto.transformToBook());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable(name = "id") Long id, @RequestBody @Valid BookUpdatedto bookdto){
		Book book = bookdto.transformToBook();
		book.setId(id);
		Book updatedBook = bookService.update(book);
		return ResponseEntity.ok(updatedBook);
	}
	// ****** obs.: o metodo update exige que informe o available no body; dá para mudar isso? (dto?) **********
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getById(@PathVariable(name="id") Long id){
		Book book = bookService.getById(id);
		return ResponseEntity.ok(book);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<Book>> listAll(@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="size", defaultValue = "10") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Book> pm = bookService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	
	@PatchMapping("/{id}/available")
	public ResponseEntity<Book> updateAvailability(@PathVariable(name="id")Long id, @RequestBody Boolean available){
		Book updatedBook = bookService.updateAvailability(id, available);
		return ResponseEntity.ok(updatedBook);
	}
	
	@GetMapping("/available")
	public ResponseEntity<List<Book>> listAllAvailable(){
		List<Book> books = bookService.listAllAvailable();
	    return ResponseEntity.ok(books);
	}
	
	//get by author, get by title... 
} 
 