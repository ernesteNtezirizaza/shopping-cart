package com.springtesting.cart.services;

import com.springtesting.cart.models.Cart;
import com.springtesting.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartService(){

    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addToCart(Integer id, String name,Double price, int quantity){
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart getCart(Integer id){
        return cartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart with id "+id+ " not found!"));
    }

    public Cart updateCart(Integer id, Cart cart){
        cartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cart not found with id"+ id));

        cart.setId(id);

        return cartRepository.save(cart);

    }


    public void deleteCart(Integer id){
        cartRepository.findById(id)
                .orElseThrow( ()->new RuntimeException("Cart not found with id"+ id));
        cartRepository.deleteById(id);
    }

    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        for(Cart cart:carts) {
            cart.setTotalCost(cart.getQuantity()*cart.getPrice());
        }

        return carts;
    }

}