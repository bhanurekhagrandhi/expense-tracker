package com.expensetracker.expense_tracker.dto;

import com.expensetracker.expense_tracker.model.Category;

import java.time.LocalDate;

public class ExpenseDTO {

    private Long id;
    private String name;
    private Double amount;
    private LocalDate date;

    private CategoryDTO category;



//    public ExpenseDTO(Long id, String name, Double amount, LocalDate date, CategoryDTO category) {
//        this.id = id;
//        this.name = name;
//        this.amount = amount;
//        this.date = date;
//        this.category = category;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
