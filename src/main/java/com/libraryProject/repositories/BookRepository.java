package com.libraryProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libraryProject.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public Optional<Book> findByTitle(String title);
	
	public List<Book> findByAuthor(String author);
	
	public List<Book> findByAvailable(Boolean available);

	public default List<Book> findAllAvailableBooks(){; 
		return findByAvailable(true);
	}
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE books SET available = :available WHERE id = :id")
	public int updateAvailability(@Param("available") Boolean available, @Param("id") Long id);
	
	// depois posso ver o findByTitleContainingIgnoreCase()
	
}
