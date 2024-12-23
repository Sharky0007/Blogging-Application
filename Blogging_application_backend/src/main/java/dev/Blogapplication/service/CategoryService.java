package dev.Blogapplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.Blogapplication.dto.Categorydto;
import dev.Blogapplication.entity.Category;
import dev.Blogapplication.exception.DataNotFoundException;
import dev.Blogapplication.repository.CategoryRepo;
import dev.Blogapplication.utils.IdGenerator;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IdGenerator idGenerator;
    
    public Categorydto createCategory(Categorydto categoryDto){

       Category category = dtoToCategory(categoryDto);
       category.setCategory_id(idGenerator.generatedId("CATEGORY-"));
       Categorydto savedCategory = categoryToDto(categoryRepo.save(category));

        return savedCategory;
    }

    public List<Categorydto> getAllCategories(){

        List<Category> categories = categoryRepo.findAll();
        List<Categorydto> res = categories.stream().map((category) -> categoryToDto(category)).collect(Collectors.toList());
        
        return res;
    }

    public Categorydto getCategoryById(String id){

        Category category = categoryRepo.findById(id).orElseThrow(()-> new DataNotFoundException("Category not found for id " + id));
        
        Categorydto res = categoryToDto(category);

        return res;
    }

    public Categorydto updateCategory(String id, Categorydto categorydto){

        Category category = categoryRepo.findById(id).orElseThrow(()-> new DataNotFoundException("Category not found for id " + id));
        
        category.setCategory_id(id);
        category.setDescription(categorydto.getDescription());
        category.setTitle(categorydto.getTitle());

        Categorydto updatedCategory = categoryToDto(categoryRepo.save(category));

        return updatedCategory;
    }

    public void deleteCategory(String id){

        categoryRepo.findById(id).orElseThrow(()-> new DataNotFoundException("Category not found for id " + id));
        categoryRepo.deleteById(id);
    }


    private Category dtoToCategory(Categorydto categorydto){

        Category category = modelMapper.map(categorydto, Category.class);

        return category;
    }

    private Categorydto categoryToDto(Category category){

        Categorydto categoryDto = modelMapper.map(category, Categorydto.class);

        return categoryDto;
    }
}
