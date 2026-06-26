package com.vivek.library.service;

import com.vivek.library.dto.CategoryRequestDto;
import com.vivek.library.dto.CategoryResponseDto;
import com.vivek.library.entity.Category;
import com.vivek.library.exception.CategoryNotFoundException;
import com.vivek.library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public CategoryResponseDto getCategoryById(Long id) {

        Category category= categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("category not found by id : "+ id));
        return mapToDto(category);
    }

    public List<CategoryResponseDto> getAllCategory(){
        List<Category> categories= categoryRepository.findAll();

        List<CategoryResponseDto> response= new ArrayList<>();

        for(Category category:categories){
            response.add(mapToDto(category));
        }

        return response;

    }

    public CategoryResponseDto saveCategory(CategoryRequestDto dto){
        Category category= mapToEntity(dto);

        Category savedCategory= categoryRepository.save(category);

        return mapToDto(savedCategory);

    }
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto){
        Category existingCategory= categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category not found with id : "+ id));


        existingCategory.setName(dto.getName());
        existingCategory.setDescription(dto.getDescription());

        Category updateCategory = categoryRepository.save(existingCategory);

        return mapToDto(updateCategory);
    }

    public void  deleteCategory(Long id){
        Category category= categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("Category not found with id : "+ id));
        categoryRepository.delete(category);
    }

   private Category mapToEntity(CategoryRequestDto dto){
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return category;

   }

   private CategoryResponseDto mapToDto(Category category){
        CategoryResponseDto response= new CategoryResponseDto();

        response.setName(category.getName());
        response.setId(category.getId());
        response.setDescription(category.getDescription());

        return response;
   }

}
