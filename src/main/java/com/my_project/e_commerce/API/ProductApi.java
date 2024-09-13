package com.my_project.e_commerce.API;

import com.my_project.e_commerce.Models.Product;
import com.my_project.e_commerce.Service.Implementation.Product_Service_Imp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "Products")
public class ProductApi {
    private final Product_Service_Imp productServiceImp;

    @GetMapping("/all")
    public ResponseEntity<?>getAll9(){
        return ResponseEntity.ok(productServiceImp.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable("id")long id){
        return ResponseEntity.ok(productServiceImp.findById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<?>saveProduct(
             @RequestBody Product product
           )
    {
      return ResponseEntity.ok(productServiceImp.save(product));
    }
    @PostMapping("/update")
    public ResponseEntity<?>update(
            @RequestBody Product product){
        return ResponseEntity.ok(update(product));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?>deleteProduct(@PathVariable("id") long id){
        return ResponseEntity.ok(productServiceImp.delete(id));
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<?>activeProduct(@PathVariable("id") long id){
        return ResponseEntity.ok(productServiceImp.activate(id));
    }

    @GetMapping("/all-with-pagination")
    public ResponseEntity<?>all_products_pagination(
            @RequestParam(defaultValue = "0") int pageN0
            ,@RequestParam(defaultValue = "3") int sizeNo){
        return ResponseEntity.ok(productServiceImp.PRODUCT_PAGE(pageN0,sizeNo));
    }
    @GetMapping("/search")
    public ResponseEntity<?>search(@RequestParam String keyword ,@RequestParam(defaultValue = "0") int pageN0){
        return ResponseEntity.ok(productServiceImp.SearchPage(pageN0,keyword));
    }
}
