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

import com.libraryProject.domain.Loan;
import com.libraryProject.domain.enums.LoanStatus;
import com.libraryProject.dto.LoanRequestdto;
import com.libraryProject.dto.LoanUpdatedto;
import com.libraryProject.model.PageModel;
import com.libraryProject.model.PageRequestModel;
import com.libraryProject.services.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "loans")
public class LoanResource {
	
	@Autowired LoanService loanService;

	@PostMapping
	public ResponseEntity<Loan> request(@RequestBody LoanRequestdto loan) 
	{
		Loan createdLoan = loanService.requestLoan(loan.getUserId(), loan.getBookId());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
	}
   
	// ***** OBS.: não permitir vários requests do mesmo empréstimo
	
	@PatchMapping("/approve/{id}")
	public ResponseEntity<Loan> approve(@PathVariable(name="id") Long id)
	{
		Loan approvedLoan = loanService.approveLoan(id);
		return ResponseEntity.ok(approvedLoan);
	}
	
	@PatchMapping("/withdraw/{id}")
	public ResponseEntity<Loan> registerWithdrawal(@PathVariable(name="id") Long id)
	{
		Loan loan  = loanService.registerWithdrawal(id);
		return ResponseEntity.ok(loan);
	}
	
	@PatchMapping("/return/{id}")
	public ResponseEntity<Loan> registerBookReturn(@PathVariable(name="id") Long id)
	{
		Loan loan  = loanService.registerBookReturn(id);
		return ResponseEntity.ok(loan);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Loan> update(@PathVariable(name="id") Long id, @RequestBody @Valid LoanUpdatedto loandto){
		Loan loan = loanService.getById(id);
		loandto.transformToLoan(loan);
		Loan updated = loanService.update(loan);
		return ResponseEntity.ok(updated);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Loan> getById(@PathVariable(name="id") Long id){
		Loan loan = loanService.getById(id);
		return ResponseEntity.ok(loan);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<Loan>> listAll(@RequestParam(value="page", defaultValue = "0") int page, 
			@RequestParam(value="size", defaultValue = "10") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Loan> pm = loanService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<Loan>> listAllByStatus(@PathVariable(name="status") LoanStatus status){
		List<Loan> loans = loanService.listAllByStatus(status);
		return ResponseEntity.ok(loans);
	}
	
	@GetMapping("/user/{userId}") 
	  public ResponseEntity<List<Loan>> listAllByUserId(@PathVariable Long userId) {
        List<Loan> loans = loanService.listAllByUserId(userId);
        return ResponseEntity.ok(loans);
    }
	
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Loan>> ListAllByBookId(@PathVariable Long bookId) {
        List<Loan> loans = loanService.listAllByBookId(bookId);
        return ResponseEntity.ok(loans);
    }
    

}
	
	
	

