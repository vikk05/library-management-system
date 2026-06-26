package com.vivek.library.controller;

import com.vivek.library.dto.CategoryRequestDto;
import com.vivek.library.dto.CategoryResponseDto;
import com.vivek.library.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService= categoryService;
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
    @GetMapping
    public List<CategoryResponseDto> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @PostMapping
    public CategoryResponseDto addCategory(@Valid @RequestBody CategoryRequestDto dto){
        return categoryService.saveCategory(dto);
    }
   @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDto dto){
        CategoryResponseDto category=categoryService.updateCategory(id,dto);

        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
         categoryService.deleteCategory(id);

         return ResponseEntity.noContent().build();
    }





}
