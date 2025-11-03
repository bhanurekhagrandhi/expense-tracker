package com.expensetracker.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @Positive(message = "Amount should be greater than 0")
    @NotNull(message = "Amount cannot be null")
    private Double amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "category_id")
    private Category category;

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + (category != null ?
                "Category{id=" + category.getId() + ", name='" + category.getName() + "'}"
                : "null") +
                '}';
    }
}
