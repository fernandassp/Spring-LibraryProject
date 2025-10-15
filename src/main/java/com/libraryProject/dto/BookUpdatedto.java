package com.libraryProject.dto;
import com.libraryProject.domain.Book;
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
	
	
	public void transformToBook(Book existingBook) {
		 existingBook.setTitle(this.title);
	     existingBook.setAuthor(this.author);
	     existingBook.setIsbn(this.isbn);
		// ao atualizar dados de um livro, o available deve permancer igual; assim, não mexe no available
		
	}
}
