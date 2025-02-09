package com.example.BBookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.BBookstore.domain.Category;
import com.example.BBookstore.domain.CategoryRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataLoader(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category();
        category1.setName("Front-End Development");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("Back-End Development");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setName("Cloud Computing");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category4.setName("Data Science");
        categoryRepository.save(category4);

        Category category5 = new Category();
        category5.setName("Biography");
        categoryRepository.save(category5);

        Category category6 = new Category();
        category6.setName("Biology");
        categoryRepository.save(category6);

        Category category7 = new Category();
        category7.setName("Chemistry");
        categoryRepository.save(category7);
         
    }
}