package com.my_project.e_commerce.Mapper;

import com.my_project.e_commerce.Dtos.OrderDetailDto;
import com.my_project.e_commerce.Dtos.OrderDto;
import com.my_project.e_commerce.Models.Order;
import com.my_project.e_commerce.Models.OrderDetail;
import com.my_project.e_commerce.Models.OrderStatus;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public OrderDto map(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderDetails( orderDetailListToOrderDetailDtoList( order.getOrderDetailList() ) );
        orderDto.setAccept( order.isAccept() );
        orderDto.setDeliveryDate( order.getDeliveryDate() );
        orderDto.setId( order.getId() );
        orderDto.setOrderDate( order.getOrderDate() );
        if ( order.getOrderStatus() != null ) {
            orderDto.setOrderStatus( order.getOrderStatus().name() );
        }
        orderDto.setPaymentMethod( order.getPaymentMethod() );
        orderDto.setQuantity( order.getQuantity() );
        orderDto.setTax( order.getTax() );
        orderDto.setTotalPrice( order.getTotalPrice() );
        orderDto.setUser( userMapper.map( order.getUser() ) );

        return orderDto;
    }

    @Override
    public Order unmap(OrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setAccept( dto.isAccept() );
        order.setDeliveryDate( dto.getDeliveryDate() );
        order.setId( dto.getId() );
        order.setOrderDate( dto.getOrderDate() );
        if ( dto.getOrderStatus() != null ) {
            order.setOrderStatus( Enum.valueOf( OrderStatus.class, dto.getOrderStatus() ) );
        }
        order.setPaymentMethod( dto.getPaymentMethod() );
        order.setQuantity( dto.getQuantity() );
        order.setTax( dto.getTax() );
        order.setTotalPrice( dto.getTotalPrice() );
        order.setUser( userMapper.unmap( dto.getUser() ) );

        return order;
    }

    protected List<OrderDetailDto> orderDetailListToOrderDetailDtoList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetailDto> list1 = new ArrayList<OrderDetailDto>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( orderDetailMapper.map( orderDetail ) );
        }

        return list1;
    }
}
