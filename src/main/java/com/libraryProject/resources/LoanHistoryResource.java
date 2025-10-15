package com.libraryProject.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryProject.domain.LoanHistory;
import com.libraryProject.model.PageModel;
import com.libraryProject.model.PageRequestModel;
import com.libraryProject.services.LoanHistoryService;

@RestController
@RequestMapping(value = "loanHistory")
public class LoanHistoryResource {
	
	@Autowired LoanHistoryService loanHistoryService;	
	
	@GetMapping("/loan/{loanId}")
	public ResponseEntity<PageModel<LoanHistory>> listAllByLoanId(@PathVariable(name = "loanId") Long loanId, 
			@RequestParam(value="page") int page, @RequestParam(value="size") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<LoanHistory> pm = loanHistoryService.listAllByLoanIdOnLazyMode(loanId, pr);
		return ResponseEntity.ok(pm);
	}
}
