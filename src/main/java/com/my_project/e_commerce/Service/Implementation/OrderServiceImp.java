package com.my_project.e_commerce.Service.Implementation;


import com.my_project.e_commerce.Dtos.OrderDto;
import com.my_project.e_commerce.Mapper.OrderMapper;
import com.my_project.e_commerce.Models.*;
import com.my_project.e_commerce.Repos.OrderDetailRepo;
import com.my_project.e_commerce.Repos.OrderRepo;
import com.my_project.e_commerce.Repos.UserRepo;
import com.my_project.e_commerce.Service.service.OrderService;
import com.my_project.e_commerce.Service.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepo repo;
    private final OrderDetailRepo orderDetailRepo;
    private final ShoppingCartService cartService;
    private final UserRepo userRepo;
    private final Product_Service_Imp productServiceImp;
    private final OrderMapper orderMapper;

    @Override
    public Order save() {
         User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         ShoppingCart cart = user.getShoppingCart();
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setUser(user);
        order.setAccept(false);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentMethod("cache");
        order.setQuantity(cart.getTotalItems());
        order.setTax(5);
        order.setTotalPrice(cart.getTotalPrice());
       List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartItem item : cart.getCartitemSet()){
           OrderDetail orderDetail = new OrderDetail();
           orderDetail.setOrder(order);
            orderDetail.setProduct(item.getProduct());
            orderDetail.setProductQuantity(item.getQuantity());
           orderDetailList.add(orderDetail);
          orderDetailRepo.save(orderDetail);
        }
       order.setOrderDetailList(orderDetailList);
        user.setOrders(List.of(order));
        userRepo.save(user);
        return repo.save(order);
    }

    @Override
    public List<OrderDto> FindAll(String username) {
      User user = userRepo.findByUsername(username).get();
       if(user.getOrders() == null){
           return null;
       }
      return (List<OrderDto>) orderMapper.map((Order) user.getOrders());
     }

    @Override
    public void acceptOrder(long id) {
     Order order = repo.findById(id).get();
     ShoppingCart cart = order.getUser().getShoppingCart();
     order.setAccept(true);
     order.setDeliveryDate(new Date());
     order.setTotalPrice(order.getTotalPrice()+order.getTax());
     cartService.deleteCartById(cart.getId());
     repo.save(order);
    }

    @Override
    public void cancelOrder(long id) {
     repo.deleteById(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return repo.findAll();
    }
}
