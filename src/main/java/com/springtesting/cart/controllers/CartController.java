package com.springtesting.cart.controllers;

import com.springtesting.cart.models.Cart;
import com.springtesting.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/get")
    public List<Cart> getAll(){
        return cartService.getAllCarts();
    }

    @PostMapping("/addToCart")
    public Cart addCart(@RequestBody Cart cart){
        return cartService.addToCart(cart.getId(),cart.getName(), cart.getPrice(), cart.getQuantity());
    }

}
