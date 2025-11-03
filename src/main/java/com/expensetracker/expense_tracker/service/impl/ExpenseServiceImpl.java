package com.expensetracker.expense_tracker.service.impl;

import com.expensetracker.expense_tracker.dto.CategoryDTO;
import com.expensetracker.expense_tracker.dto.ExpenseDTO;
import com.expensetracker.expense_tracker.exception.ResourceNotFoundException;
import com.expensetracker.expense_tracker.model.Category;
import com.expensetracker.expense_tracker.model.Expense;
import com.expensetracker.expense_tracker.repository.CategoryRepository;
import com.expensetracker.expense_tracker.repository.ExpenseRepository;
import com.expensetracker.expense_tracker.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    private ExpenseDTO convertToDTO(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setName(expense.getName());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());

        if (expense.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(expense.getCategory().getId());
            categoryDTO.setName(expense.getCategory().getName());
            dto.setCategory(categoryDTO);
        }

        return dto;
    }

    @Override
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        log.info("Attempting to save expense: {}", expenseDTO);


        Category category = categoryRepository.findById(expenseDTO.getCategory().getId()).
                orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + expenseDTO.getCategory().getId()));

        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        expense.setCategory(category);

        Expense saved = expenseRepository.save(expense);
        log.debug("Saved expense details: {}", saved);
        return convertToDTO(saved);
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        return category;
    }

    @Override
    public Expense getExpensebyId(Long id) {
        log.info("Fetching expense by ID: {}", id);
        return expenseRepository.findById(id).orElseThrow(() ->
                {
                    log.error("Expense not found with id: {}", id);
                    return new ResourceNotFoundException("Expense not found with id: " + id);
                }
        );
    }

    @Override
    public List<Expense> getAllExpenses() {
        log.info("Fetching all expenses from database");
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getExpensesByCategory(Long categoryId) {
        log.info("Fetching expenses for category ID: {}", categoryId);
        List<Expense> expenses = expenseRepository.findByCategoryId(categoryId);
        if (expenses.isEmpty()) {
            log.warn("No expenses found for category ID: {}", categoryId);
        }
        return expenses;
    }

    @Override
    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        log.info("Updating expense with ID: {}", id);

        Expense existingExpense = getExpensebyId(id);
        Category category = getCategoryById(expenseDTO.getCategory().getId());


        log.debug("Current expense data: {}", existingExpense);

        modelMapper.map(expenseDTO, existingExpense);

        existingExpense.setName(expenseDTO.getName());
        existingExpense.setDate(expenseDTO.getDate());
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setCategory(category);

        Expense savedExpense = expenseRepository.save(existingExpense);

        log.info("Expense with ID {} updated successfully", id);
        log.debug("Updated expense details: {}", savedExpense);

        return savedExpense;
    }

    @Override
    public Expense updateExpensePartially(Long id, ExpenseDTO expenseDTO) {
        log.info("Partially updating expense with ID: {}", id);
        Expense existingExpense = getExpensebyId(id);
        Category category = getCategoryById(expenseDTO.getCategory().getId());
        if (expenseDTO.getDate() != null) {
            existingExpense.setDate(expenseDTO.getDate());
            log.debug("Updated date for expense ID {}", id);
        }

        if (expenseDTO.getAmount() != null) {
            existingExpense.setAmount(expenseDTO.getAmount());
            log.debug("Updated amount for expense ID {}", id);
        }

        if (expenseDTO.getName() != null) {
            existingExpense.setName(expenseDTO.getName());
            log.debug("Updated name for expense ID {}", id);
        }

        Expense saved = expenseRepository.save(existingExpense);
        log.info("Expense with ID {} updated partially", id);
        return saved;
    }

    @Override
    public void deleteExpense(Long id) {
        log.info("Deleting expense with ID: {}", id);
        Expense expense = getExpensebyId(id);
        expenseRepository.delete(expense);
        log.info("Expense deleted successfully with ID: {}", id);
    }


}
