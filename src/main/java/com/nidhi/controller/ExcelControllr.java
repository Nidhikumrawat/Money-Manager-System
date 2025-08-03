package com.nidhi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nidhi.service.ExcelService;
import com.nidhi.service.ExpenseService;
import com.nidhi.service.IncomeService;
import java.io.IOException; 
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelControllr {
	private final ExcelService excelService;
	private final IncomeService incomeService;
	private final ExpenseService expenseService;
	
	@GetMapping("/download/income")
	public void downloadIncomeExcel(HttpServletResponse response) throws IOException {
	  response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	  response.setHeader("Content-Disposition", "attachment; filename=income.xlsx");
	  excelService.writeIncomesToExcel(response.getOutputStream(), incomeService.getCurrentMonthIncomeForCurrentUser());
	
	}
	

	@GetMapping("/download/expense")
	public void downloadExpenseExcel(HttpServletResponse response) throws IOException {
	  response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	  response.setHeader("Content-Disposition", "attachment; filename=income.xlsx");
	  excelService.writeExpensesToExcel(response.getOutputStream(), expenseService.getCurrentMonthExpenseForCurrentUser());
	  
	}

}
