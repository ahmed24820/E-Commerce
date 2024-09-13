package com.my_project.e_commerce.Service.Implementation;

import com.my_project.e_commerce.Exceptions.NoSuchElements;
import com.my_project.e_commerce.Models.Product;
import com.my_project.e_commerce.Repos.ProductRepo;
import com.my_project.e_commerce.Service.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Product_Service_Imp implements productService {
    private final ProductRepo repo;
    private final FileServiceImplementation uploadFile;

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Product findById(long id) {
        return repo.findById(id).orElseThrow(()-> new NoSuchElements("product not found"));
    }

    @Override
    public Product save( Product product) {
        Product savedOne = new Product();
        savedOne.setName(product.getName());
        savedOne.setDescription(product.getDescription());
        savedOne.setCategory(product.getCategory());
        savedOne.setSalePrice(product.getSalePrice());
        savedOne.setCurrentQuantity(product.getCurrentQuantity());
        savedOne.setCostPrice(product.getCostPrice());
        savedOne.setDeleted(false);
        savedOne.setActivated(true);

        return repo.save(savedOne);
        }


    @Override
    public Product update(Product product) {
      Product updatedProduct = repo.findById(product.getId()).get();
            updatedProduct.setName(product.getName());
            updatedProduct.setCostPrice(product.getCostPrice());
            updatedProduct.setCurrentQuantity(product.getCurrentQuantity());
            updatedProduct.setSalePrice(product.getSalePrice());
            updatedProduct.setCategory(product.getCategory());
            updatedProduct.setActivated(product.isActivated());
            updatedProduct.setDeleted(product.isDeleted());
            return repo.save(updatedProduct);
        }

        @Override
    public String delete(long id) {
      Product product = repo.getReferenceById(id);
      product.setDeleted(true);
      product.setActivated(false);
      repo.save(product);
      return "Deleted Successfully";
    }
    @Override
    public String activate(long id) {
        Product product = repo.getReferenceById(id);
        product.setDeleted(false);
        product.setActivated(true);
        repo.save(product);
        return "Activated Successfully";
    }

    @Override
    public Product findByname(String name) {
        return repo.findByName(name);
    }

    @Override
    public Page<Product> PRODUCT_PAGE(int pageNo,int sizeNo) {
         Pageable pageable = PageRequest.of(pageNo,sizeNo);
        return repo.findAll(pageable);
    }

    @Override
    public Page<Product> SearchPage(int pageN0, String keyword) {
        Pageable pageable = PageRequest.of(pageN0 ,4);
        return repo.SearchProduct(keyword,pageable);
    }


    /*
    * this method to transfer the list that we have to a page we will check for if the list is empty or not
    * if it is not we will make a start point and end point and make a new page with the sublist and pageable
    * and the end of it is the list size
    * */
//    private Page<Product>ToPage(List list , Pageable pageable){
//       if (pageable.getOffset() >= list.size()){
//           return Page.empty();
//       }
//       int startIndex = (int) pageable.getOffset();
//        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
//                ? list.size()
//                : (int) (pageable.getOffset() + pageable.getPageSize());
//        List sublist = list.subList(startIndex,endIndex);
//        return new PageImpl(sublist,pageable,list.size());
//    }

}
