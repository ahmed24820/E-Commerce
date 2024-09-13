package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.OrderDetailDto;
import com.my_project.e_commerce.Models.OrderDetail;
import org.mapstruct.Mapper;

@Mapper(imports ={ProductMapper.class})
public interface OrderDetailMapper {
    OrderDetailDto map (OrderDetail orderDetail);
    OrderDetail unmap (OrderDetailDto orderDetailDto);

}
