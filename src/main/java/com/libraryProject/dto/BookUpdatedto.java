package com.libraryProject.dto;

import java.util.ArrayList;
import java.util.List;

import com.libraryProject.domain.Book;
import com.libraryProject.domain.Loan;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookUpdatedto {

	@NotBlank(message = "Title required")
	private String title;
	
	@NotBlank(message = "Author required")
	private String author;
	
	@NotBlank(message = "ISBN required")
	@Size(min = 13, max = 17, message = "ISBN length must be 13 (only numbers) or 17 (numbers and ' - ')")
	private String isbn;
	
	private Boolean available;
	
	private List<Loan> loans = new ArrayList<>();
	
	
	public Book transformToBook() {
		Book book = new Book(null, title, author, isbn, available, loans);  
		// ao atualizar dados de um livro, o available deve permancer igual;
		// update availability também não pode ser chamado sem ser no approve ou return loan
		return book;
	}
}
