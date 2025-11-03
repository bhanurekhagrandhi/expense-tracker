package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.dto.ExpenseDTO;
import com.expensetracker.expense_tracker.model.Expense;
import com.expensetracker.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@Slf4j
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<ExpenseDTO> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        log.info("POST /api/expenses called with expense:{}",expenseDTO);
        ExpenseDTO saved =  expenseService.createExpense(expenseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses() {
        log.info("GET /api/expenses called");
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {
        log.info("PUT /api/expenses called with expense:{}",expenseDTO);
        Expense updatedExpense = expenseService.updateExpense(id,expenseDTO);
        return ResponseEntity.ok(expenseService.updateExpense(id,expenseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        log.info("DELETE /api/expenses called with id:{}",id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Expense> updateExpensePartially(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        log.info("PATCH /api/expenses called with expense:{}",expenseDTO);
        return ResponseEntity.ok(expenseService.updateExpensePartially(id,expenseDTO));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable Long id) {
        log.info("GET /api/category called with id:{}",id);
        return ResponseEntity.ok(expenseService.getExpensesByCategory(id));
    }

}
