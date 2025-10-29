package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.dto.ExpenseResponse;
import com.expensetracker.expense_tracker.exception.ResourceNotFoundException;
import com.expensetracker.expense_tracker.model.Expense;
import com.expensetracker.expense_tracker.repository.ExpenseRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    public ExpenseController(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @PostMapping
    public Expense addExpense(@Valid @RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @GetMapping
    public List<ExpenseResponse> getExpenses() {
        return expenseRepository.findAll().stream().map(exp->new ExpenseResponse(
                exp.getId(),
                exp.getName(),
                exp.getAmount(),
                exp.getDate(),
                exp.getCategory()!=null?exp.getCategory().getName():null
        )).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @Valid @RequestBody Expense expenseRequest) {
        return expenseRepository.findById(id).map((expense -> {
            expense.setName(expenseRequest.getName());
            expense.setAmount(expenseRequest.getAmount());
            expense.setDate(expenseRequest.getDate());
            Expense updated = expenseRepository.save(expense);
            return ResponseEntity.ok(updated);
        })).orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        return expenseRepository.findById(id).map(expense -> {
            expenseRepository.delete(expense);
            return ResponseEntity.ok("Deleted succesfully");
        }).orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
    }

    @PatchMapping("/{id}")
    public Expense updateExpensePartially(@PathVariable Long id, @RequestBody Expense updatedFields) {
        return expenseRepository.findById(id).map(existingExpense -> {
            if (updatedFields.getName() != null) {
                existingExpense.setName(updatedFields.getName());
            }

            if (updatedFields.getAmount() != null) {
                existingExpense.setAmount(updatedFields.getAmount());
            }

            if (updatedFields.getDate() != null) {
                existingExpense.setDate(updatedFields.getDate());
            }

            return expenseRepository.save(existingExpense);
        }).orElseThrow(() -> new ResourceNotFoundException("Expense not found with id " + id));
    }

    @GetMapping("/category/{id}")
    public List<Expense> getExpensesByCategory(@PathVariable Long id) {
        return expenseRepository.findByCategoryId(id);
    }

}
