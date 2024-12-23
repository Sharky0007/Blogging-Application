package dev.Blogapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Blogapplication.dto.Categorydto;
import dev.Blogapplication.exception.DataNotFoundException;
import dev.Blogapplication.payload.ApiResponse;
import dev.Blogapplication.service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/create")
    public ResponseEntity<Categorydto> createCategory(@Valid @RequestBody Categorydto categorydto){

        Categorydto savedCategory = categoryService.createCategory(categorydto);

        return new ResponseEntity<Categorydto>(savedCategory, HttpStatus.OK);

    }

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<Categorydto>> getAllCategories(){

        List<Categorydto> res = categoryService.getAllCategories();

        return new ResponseEntity<List<Categorydto>>(res, HttpStatus.OK);
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<Categorydto> getCategoryById(@PathVariable("id") String id) throws DataNotFoundException{

        Categorydto res = categoryService.getCategoryById(id);
        return new ResponseEntity<Categorydto>(res, HttpStatus.OK);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Categorydto> updateCategory(@PathVariable("id") String id, @Valid @RequestBody Categorydto categorydto){

        Categorydto updatedCategory = categoryService.updateCategory(id, categorydto);
        return new ResponseEntity<Categorydto>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") String id){

        ApiResponse apiResponse = new ApiResponse();
        categoryService.deleteCategory(id);

        apiResponse.setMessage("Categoryid " + id + " deleted successfully");
        apiResponse.setStatus(HttpStatus.OK);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    } 
}
