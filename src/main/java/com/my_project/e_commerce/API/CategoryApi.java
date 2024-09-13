package com.my_project.e_commerce.API;

import com.my_project.e_commerce.Dtos.CategoryDto;
import com.my_project.e_commerce.Mapper.CategoryMapper;
import com.my_project.e_commerce.Models.Category;
import com.my_project.e_commerce.Service.Implementation.CategoryServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Tag(name = "Category")
public class CategoryApi {
    private final CategoryServiceImp serviceImp;
    private final CategoryMapper categoryMapper;

    @GetMapping("/All")
    public ResponseEntity<?>allCategories(){
        return ResponseEntity.ok(serviceImp.Categories());
    }

    @GetMapping("/All-active")
    public ResponseEntity<?>all_Active_Categories(){
        return ResponseEntity.ok(serviceImp.Categories_Activated());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable("id") long id){
        return ResponseEntity.ok(categoryMapper.map(serviceImp.findById(id)));

    }
    @PostMapping("/add")
    public ResponseEntity<?>addingCategory(@RequestBody Category category){
       return ResponseEntity.ok(serviceImp.save(category));
    }
    @PutMapping("/update")
    public ResponseEntity<?>updateCategory(@RequestParam long id , @RequestBody Category category){
        return ResponseEntity.ok(serviceImp.update(id,category));
    }
    @GetMapping("/activate/{id}")
    public ResponseEntity<?>active(@PathVariable("id") long id){
       return ResponseEntity.ok(serviceImp.Activate(id));
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable("id") long id){
        return ResponseEntity.ok(serviceImp.Delete(id));
    }




}
