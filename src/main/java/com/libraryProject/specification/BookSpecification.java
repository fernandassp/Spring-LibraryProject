package com.libraryProject.specification;

import org.springframework.data.jpa.domain.Specification;

import com.libraryProject.domain.Book;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BookSpecification {
	public static Specification<Book> search(String text){
		return new Specification<Book>() {

			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				if(text == null || text.trim().length() <= 0) {
					return null;
				}
				String likeTerm = "%" + text + "%" ;
				
				Predicate predicate = cb.or(cb.like(root.get("title"), likeTerm),
						cb.like(root.get("author"), likeTerm),
						cb.like(root.get("isbn"), likeTerm)
						);
				
				return predicate;
			}
			
		};
	}
}
