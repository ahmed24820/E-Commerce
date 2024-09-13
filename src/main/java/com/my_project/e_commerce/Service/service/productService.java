package com.my_project.e_commerce.Service.service;

import com.my_project.e_commerce.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface productService {
    List<Product>findAll();
    Product findById(long id);
    Product save( Product product);
    Product update(Product product);
    String delete(long id);
    String activate(long id);
    Product findByname(String name);
    Page<Product>PRODUCT_PAGE(int pageNo,int sizeNO);
    Page<Product>SearchPage(int pageN0, String keyword);

}
