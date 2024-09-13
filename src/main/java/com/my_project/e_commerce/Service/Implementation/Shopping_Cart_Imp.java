package com.my_project.e_commerce.Service.Implementation;


import com.my_project.e_commerce.Models.CartItem;
import com.my_project.e_commerce.Models.Product;
import com.my_project.e_commerce.Models.ShoppingCart;
import com.my_project.e_commerce.Models.User;
import com.my_project.e_commerce.Repos.CartItemRepo;
import com.my_project.e_commerce.Repos.ShoppingCartRepo;
import com.my_project.e_commerce.Repos.UserRepo;
import com.my_project.e_commerce.Service.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class Shopping_Cart_Imp implements ShoppingCartService {
    private final UserRepo userRepo;
    private final CartItemRepo itemRepo;
    private final ShoppingCartRepo cartRepo;
    private final Product_Service_Imp productServiceImp;

    @Override
    @Transactional
    public void addItemToCart(int product_id, int quantity) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ShoppingCart cart = user.getShoppingCart();

        Product product = productServiceImp.findById(product_id);

        if (cart == null){
            cart = new ShoppingCart();
        }
        CartItem item = (findItem(product_id, cart));
        Set<CartItem> cartItems = cart.getCartitemSet();

        int itemQuantity = 0;
          if (cartItems == null){
             cartItems = new HashSet<>();
          }
             if (item == null) {
                item = new CartItem();
                item.setQuantity(quantity);
                item.setUnitPrice(product.getCostPrice());
                item.setProduct(product);
                item.setShoppingCart(cart);
                cartItems.add(item);
                itemRepo.save(item);
            }else {
                itemQuantity = item.getQuantity() + quantity;
                item.setQuantity(itemQuantity);
                itemRepo.save(item);
            }
             cart.setCartitemSet(cartItems);

             double totalPrice = TotalPrice(cartItems);
             int totalItems = TotalItem(cartItems);

            cart.setTotalItems(totalItems);
            cart.setTotalPrice(totalPrice);
            cart.setUser(user);
            cartRepo.save(cart);

            user.setShoppingCart(cart);
           userRepo.save(user);
    }


    @Override
    public void UpdateCartItem(long productId, int quantity) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ShoppingCart cart = user.getShoppingCart();

         Set<CartItem> cartItems = cart.getCartitemSet();
         CartItem item = findItem(productId,cart);


         item.setQuantity(quantity);
         itemRepo.save(item);
         cart.setCartitemSet(cartItems);

         double totalPrice = TotalPrice(cartItems);
         int totalItems = TotalItem(cartItems);

         cart.setTotalItems(totalItems);
         cart.setTotalPrice(totalPrice);

         cartRepo.save(cart);
    }

    @Override
    public void deleteCartById(long id) {
       ShoppingCart cart = cartRepo.findById(id).orElseThrow();
        // we will check for if the cart is full and if true, we will clear every item in it
       if (!ObjectUtils.isEmpty(cart) && !ObjectUtils.isEmpty(cart.getCartitemSet())){
           itemRepo.deleteAll(cart.getCartitemSet());
       }
       cart.getCartitemSet().clear();
       cart.setTotalPrice(0);
       cart.setTotalItems(0);
       cartRepo.save(cart);
    }

    @Override
    public void removeItemFromCart(long productId, long cartId) {
        ShoppingCart cart = cartRepo.findById(cartId).orElseThrow();

       CartItem cartItem =  cart.getCartitemSet()
                .stream()
                .filter(item -> item.getProduct().getId()==(productId))
                .findFirst().orElseThrow();
        cart.getCartitemSet().remove(cartItem);
        cart.setTotalItems(TotalItem(cart.getCartitemSet()));
        cart.setTotalPrice(cart.getTotalPrice());
         cartRepo.save(cart);
    }

    public ShoppingCart getByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (user.getShoppingCart() == null){
             return new ShoppingCart();
         }
        return user.getShoppingCart();
    }



    private CartItem findItem(long productId , ShoppingCart cart){
        if ( cart == null || cart.getCartitemSet()== null){
            return null;
        }
        CartItem cartItem = null;
        for (CartItem item :cart.getCartitemSet()){
              if (item.getProduct().getId() == productId){
                  cartItem = item;
              }
           }
        return cartItem;
    }



    //this method will help us to calc the price for every item the use takes in his cart
    private double TotalPrice(Set<CartItem> cartItems){
        double total = 0;
        for (CartItem item : cartItems){
         total += item.getUnitPrice() * item.getQuantity();
        }
        return total;
    }
     // this method will help us to calc the number of items that we have in our cart
    private int TotalItem(Set<CartItem> cartItems){
        int num = 0;
        for (CartItem item : cartItems){
           num += item.getQuantity();
        }
        return num;
    }

}
