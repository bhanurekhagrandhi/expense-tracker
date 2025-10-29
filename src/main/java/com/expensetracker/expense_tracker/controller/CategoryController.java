package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.model.Category;
import com.expensetracker.expense_tracker.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public Category addCategory(@Valid @RequestBody Category category){
        return categoryRepository.save(category);
    }

    @GetMapping
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
