package com.my_project.e_commerce.API;

import com.my_project.e_commerce.Common.apiResponse;
import com.my_project.e_commerce.Exceptions.NoSuchElements;
import com.my_project.e_commerce.Mapper.CartMapper;
import com.my_project.e_commerce.Repos.CartItemRepo;
import com.my_project.e_commerce.Service.Implementation.Product_Service_Imp;
import com.my_project.e_commerce.Service.Implementation.Shopping_Cart_Imp;
import com.my_project.e_commerce.Service.Implementation.UserServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart")
public class cartApi {
    private final Shopping_Cart_Imp shoppingCartImp;
    private final CartItemRepo cartItemRepo;
    private final UserServiceImp userService;
    private final Product_Service_Imp productServiceImp;
    private final CartMapper cartMapper;

    @Operation(
            description = "this api help the customer to get his shopping cart",
            summary = "retrieving  cart for the user",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/my-cart")
    public ResponseEntity<apiResponse>cart(){
      return ResponseEntity.ok( new apiResponse(
              "founded successfully",cartMapper.map(shoppingCartImp.getByUser())));
    }

    @Operation(
            description = "this api help the customer to add item he want to his cart",
            summary = "Adding items to cart",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )
            }
    )

    @PostMapping("/add-item")
    public ResponseEntity<apiResponse>add_item_Cart(
            @RequestParam int product_id,
            @RequestParam int quantity
      ){
        shoppingCartImp.addItemToCart(product_id,quantity);
        return ResponseEntity.ok(new apiResponse("success",null));
    }
    @Operation(
            description = "this api help the customer to update his cart",
            summary = "Updating items in cart",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )
            }
    )

    @PutMapping("/update-cart")
    public ResponseEntity<?>updateCart(@RequestParam long id ,@RequestParam int quantity ){
         shoppingCartImp.UpdateCartItem(id,quantity);
        return ResponseEntity.ok(new apiResponse("updated successfully",null));

    }
    @Operation(
            description = "this api help the customer to delete items in his cart",
            summary = "Deleting items in cart",
            responses = {
                    @ApiResponse(description = "success",
                            responseCode = "200",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "UN AUTHORIZED / JWT EXPIRED",
                            responseCode = "403",
                            content =@Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "AN INTERNAL ERROR",
                            responseCode = "501",
                            content =@Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/delete-cart")
    public ResponseEntity<?> deleteCart(@RequestParam long id){
        shoppingCartImp.deleteCartById(id);
        return ResponseEntity.ok(new apiResponse("deleted successfully",null));
    }
}
