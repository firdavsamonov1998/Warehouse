package com.company.warehouse.controller;

import com.company.warehouse.entity.Category;
import com.company.warehouse.payload.CategoryDTO;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add") //this method adds new category
    private Message add(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.add(categoryDTO);
    }

    @GetMapping("/get") //this method returns all category
    private List<Category> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/get/{id}") // this method returns by category id
    private Category getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @PutMapping("/edite/{id}") // this method updates category data in DB
    private Message edite(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.edite(id, categoryDTO);
    }

    @DeleteMapping("/delete/{id}") // this method deletes the category data from DB
    private Message delete(@PathVariable Integer id) {
        return categoryService.delete(id);
    }
}
