package com.libraryProject.domain.enums;

public enum LoanStatus {

	REQUESTED,  // Usuário solicitou o empréstimo
	APPROVED,   // Bibliotecário aprovou
	IN_PROGRESS,// Livro foi retirado
	RETURNED,   // Livro foi devolvido
	OVERDUE,    // Atrasado na devolução
	CANCELLED   // Empréstimo cancelado
}
