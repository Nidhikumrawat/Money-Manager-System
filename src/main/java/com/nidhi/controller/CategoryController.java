package com.nidhi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nidhi.dto.CategoryDTO;
import com.nidhi.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
 
	
	private final CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO){
		CategoryDTO saveCategory = categoryService.saveCategory(categoryDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveCategory);
	}
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<CategoryDTO> categories = categoryService.getCategoriesForCurrentUser();
		return ResponseEntity.ok(categories);
}
	@GetMapping("/{type}")
	public ResponseEntity<List<CategoryDTO>> getCategoriesByTypeForCurrentUser(@PathVariable String type){
		List<CategoryDTO> list = categoryService.getCategoriesByTypeForCurrentUser(type);
		return ResponseEntity.ok(list);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId
			,@RequestBody CategoryDTO categoryDTO){
		CategoryDTO updateCategory = categoryService.updateCategory(categoryId, categoryDTO);
				return  ResponseEntity.ok(updateCategory);
	}
	
//	@PutMapping("/categories/{id}/update-type")
//	public ResponseEntity<CategoryDTO> updateCategoryType(
//	        @PathVariable Long id,
//	        @RequestParam String newType) {
//	    return ResponseEntity.ok(categoryService.updateCategoryType(id, newType));
//	}

	@PutMapping("/categories/{id}/update-type")
	public ResponseEntity<CategoryDTO> updateCategoryType(
	        @PathVariable("id") Long categoryId,
	        @RequestParam("newType") String newType
	) {
	    CategoryDTO updatedCategory = categoryService.updateCategoryType(categoryId, newType);
	    return ResponseEntity.ok(updatedCategory);
	}
	}

