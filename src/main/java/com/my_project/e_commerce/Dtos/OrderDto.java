package com.my_project.e_commerce.Dtos;

import com.my_project.e_commerce.Models.OrderDetail;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private Date orderDate;
    private Date deliveryDate;
    private String orderStatus;
    private double totalPrice;
    private double tax;
    private int quantity;
    private String paymentMethod;
    private boolean isAccept;
    private userDto user;
    private List<OrderDetailDto>orderDetails;
}
