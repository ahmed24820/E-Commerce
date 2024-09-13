package com.my_project.e_commerce.API;

import com.my_project.e_commerce.Common.apiResponse;
import com.my_project.e_commerce.Dtos.OrderDto;
import com.my_project.e_commerce.Mapper.OrderMapper;
import com.my_project.e_commerce.Models.Order;
import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Service.Implementation.OrderServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Tag(name = "Orders")
public class OrderApi {
    private final OrderServiceImp orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/checkout")
    public ResponseEntity<?>checkout(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       OrderDto orderDto = orderMapper.map(orderService.save());
      return ResponseEntity.ok(new apiResponse("Order Saved Successfully",orderDto));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<?>findOrdersByEmail(@PathVariable("email") String email){
      List<OrderDto> orderDtoList = orderService.FindAll(email);
      return ResponseEntity.ok(new apiResponse("All user Orders are ",orderDtoList));
    }

    @GetMapping("/accept-order")
    public ResponseEntity<?>accept_order(@RequestParam("order-id") long id){
        orderService.acceptOrder(id);
        return ResponseEntity.ok(new apiResponse("order accepted successfully",null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?>all_orders(){
       List<Order> ALlOrders = orderService.findAllOrders();
       List<OrderDto> orderDtoList = new ArrayList<>();
       for (Order order : ALlOrders){
           OrderDto dto = orderMapper.map(order);
           orderDtoList.add(dto);
       }
       return ResponseEntity.ok(new apiResponse("All orders in our database", orderDtoList));
    }
}
