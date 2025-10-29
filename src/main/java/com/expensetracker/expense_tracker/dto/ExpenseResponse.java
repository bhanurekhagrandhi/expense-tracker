package com.expensetracker.expense_tracker.dto;

import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String name;
    private Double amount;
    private LocalDate date;
    private String categoryName;

    public ExpenseResponse(Long id, String name,Double amount,LocalDate date, String categoryName){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.categoryName = categoryName;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
