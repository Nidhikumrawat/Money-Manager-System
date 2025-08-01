package com.nidhi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nidhi.dto.ExpenseDTO;
import com.nidhi.dto.IncomeDTO;
import com.nidhi.service.ExpenseService;
import com.nidhi.service.IncomeService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incomes")
public class IncomeController {
	 
	private final IncomeService incomeService;
	
	@PostMapping
	public ResponseEntity<IncomeDTO> addExpense(@RequestBody IncomeDTO dto){
		IncomeDTO saved = incomeService.addIncome(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@GetMapping
	public ResponseEntity<List<IncomeDTO>> getExpenses(){
		List<IncomeDTO> expenses = incomeService.getCurrentMonthIncomeForCurrentUser();
		return ResponseEntity.ok(expenses);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIncome(@PathVariable Long id){
		incomeService.deleteIncome(id);
		return ResponseEntity.noContent().build();
	}
	
	 @GetMapping("/excel/download/income")
	    public void downloadIncomeExcel(HttpServletResponse response) {
	        incomeService.exportIncomeToExcel(response);
	    }


}
