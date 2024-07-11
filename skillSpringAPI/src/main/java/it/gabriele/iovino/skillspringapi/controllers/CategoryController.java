package it.gabriele.iovino.skillspringapi.controllers;

import it.gabriele.iovino.skillspringapi.dto.CategoryDTO;
import it.gabriele.iovino.skillspringapi.models.Category;
import it.gabriele.iovino.skillspringapi.services.CategoryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /* ------------   GET   ------------ */

    @GET
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GET
    @Path("/{id}")
    public Category getCategoryById(@PathVariable int id){
        return categoryService.getCategoryById(id);
    }


    /* ------------   POST   ------------ */

    @POST
    public Category createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }

    /* ------------   PUT   ------------ */

    @PUT
    @Path("/{id}")
    public Category editCategory(@RequestBody CategoryDTO categoryDTO, @PathParam("id") int categoryId){
        return categoryService.editCategory(categoryDTO, categoryId);
    }

    /* ------------   DELETE   ------------ */

    @DELETE
    @Path("/{id}")
    public void deleteCategoryById(@PathVariable int id){
        categoryService.deleteCategoryById(id);
    }
}
