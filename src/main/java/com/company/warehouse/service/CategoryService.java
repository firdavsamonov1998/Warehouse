package com.company.warehouse.service;

import com.company.warehouse.entity.Category;
import com.company.warehouse.payload.CategoryDTO;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Message add(CategoryDTO categoryDTO) {

        boolean existsCategoryByName = categoryRepository.existsCategoryByName(categoryDTO.getName());

        if (existsCategoryByName) return new Message("This category is already exist", false);

        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParentCategoryId() != null) {
            Optional<Category> optional = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (optional.isEmpty()) return new Message("Parent category not founded", false);
            Category optionalCategory = optional.get();
            category.setParentCategory(optionalCategory);
        }

        categoryRepository.save(category);
        return new Message("Successfully saved", true);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) return new Category();
        return optional.get();
    }

    public Message edite(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);

        Category category = optional.get();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentCategoryId() != null) {
            Category parentCategory = category.getParentCategory();
            category.setParentCategory(parentCategory);
        }
        categoryRepository.save(category);
        return new Message("Successfully updated", true);
    }

    public Message delete(Integer id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        categoryRepository.deleteById(id);
        return new Message("Successfully deleted", true);
    }
}
