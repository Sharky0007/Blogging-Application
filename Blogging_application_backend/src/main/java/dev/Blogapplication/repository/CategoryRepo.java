package dev.Blogapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.Blogapplication.entity.Category;

@Repository
public interface CategoryRepo extends MongoRepository<Category,String>{
    
}
