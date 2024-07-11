package it.gabriele.iovino.skillspringapi.services;

import it.gabriele.iovino.skillspringapi.dto.CategoryDTO;
import it.gabriele.iovino.skillspringapi.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    // GET
    List<Category> getAllCategories();
    Category getCategoryById(int id);

    // POST
    Category createCategory(CategoryDTO categoryDTO);

    // PUT
    Category editCategory(CategoryDTO categoryDTO, int cateogryId);

    // DELETE
    void deleteCategoryById(int id);
}
