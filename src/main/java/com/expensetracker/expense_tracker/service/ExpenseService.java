package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dto.ExpenseDTO;
import com.expensetracker.expense_tracker.model.Category;
import com.expensetracker.expense_tracker.model.Expense;

import java.util.List;

public interface ExpenseService {
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);

    Expense getExpensebyId(Long id);

    List<Expense> getAllExpenses();

    List<Expense> getExpensesByCategory(Long categoryId);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    Expense updateExpensePartially(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

    Category getCategoryById(Long id);
}
