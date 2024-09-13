package com.my_project.e_commerce.Service.service;


public interface ShoppingCartService {
    void addItemToCart(int product_id, int quantity);
    void UpdateCartItem(long id, int quantity);
    void deleteCartById(long id);
    void removeItemFromCart(long productId , long cartId);
}
