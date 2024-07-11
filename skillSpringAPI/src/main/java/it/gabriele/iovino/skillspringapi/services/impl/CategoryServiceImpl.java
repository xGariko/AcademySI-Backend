package it.gabriele.iovino.skillspringapi.services.impl;

import it.gabriele.iovino.skillspringapi.dao.CategoryDAO;
import it.gabriele.iovino.skillspringapi.dto.CategoryDTO;
import it.gabriele.iovino.skillspringapi.models.Category;
import it.gabriele.iovino.skillspringapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public Category editCategory(CategoryDTO categoryDTO, int categoryId) {
        Category selectedCategory = this.getCategoryById(categoryId);
        if(selectedCategory == null){
            return null;
        }

        selectedCategory.setImgUrl(categoryDTO.getImgUrl());
        selectedCategory.setName(categoryDTO.getName());

        return categoryDAO.save(selectedCategory);
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDAO.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory.setImgUrl(categoryDTO.getImgUrl());
        return categoryDAO.save(newCategory);
    }

    @Override
    public void deleteCategoryById(int id) {
        categoryDAO.deleteById(id);
    }
}
