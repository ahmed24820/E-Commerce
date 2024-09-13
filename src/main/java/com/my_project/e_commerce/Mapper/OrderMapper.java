package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.OrderDto;
import com.my_project.e_commerce.Models.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {OrderDetailMapper.class,UserMapper.class})
public interface OrderMapper {
    @Mapping(source = "orderDetailList",target = "orderDetails")
    OrderDto map(Order order);
    Order unmap(OrderDto dto);
}
