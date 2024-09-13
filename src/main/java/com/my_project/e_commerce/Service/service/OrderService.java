package com.my_project.e_commerce.Service.service;


import com.my_project.e_commerce.Dtos.OrderDto;
import com.my_project.e_commerce.Models.Order;
import com.my_project.e_commerce.Models.ShoppingCart;

import java.util.List;

public interface OrderService {
    Order save();
    List<OrderDto> FindAll(String username);
    void acceptOrder(long id);
    void cancelOrder(long id);
    List<Order> findAllOrders();


}
