package com.springtesting.cart;

import com.springtesting.cart.models.Cart;
import com.springtesting.cart.repository.CartRepository;
import com.springtesting.cart.services.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServicesTest {

    @Mock
    CartRepository cartRepository;

    @InjectMocks
    CartService cartService;


    @Test
    public void returnCarts(){
        when(cartRepository.findAll()).thenReturn(Arrays.asList(new Cart(1,"Cart 1",200,3),new Cart(2,"Cart 2",200,3)));
        assertEquals("Other",cartService.getAllCarts().get(2));
    }

    @Test
    public void createCartItem(){

            when(cartRepository.save(ArgumentMatchers.any(Cart.class))).thenReturn(new Cart(3,"Cart 3",200,3));
            assertEquals("Cart 3",cartService.addToCart(3,"Cart 4",400,3));
    }
    @Test
    public void removeCart(){
        Cart cart = new Cart(5,"Cart 5",500,3);
        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        cartService.deleteCart(cart.getId());

        verify(cartRepository).deleteById(cart.getId());

    }

    @Test
    public void updateCart(){
        Cart cart = new Cart(6,"Cart 6",600,3');
        Cart newCart = new Cart("Cart 6 updated",700);
        given(cartRepository.findById(cart.getId())).willReturn(Optional.of(cart));

        cartService.updateCart(cart.getId(),newCart);
        verify(cartRepository).save(newCart);
        verify(cartRepository).findById(cart.getId());
    }

    @Test
    public void returnCart(){
        Cart cart = new Cart();
        cart.setId(2);

        when(cartRepository.findById(cart.getId())).thenReturn(Optional.of(cart));

        Cart expected = cartService.getCart(cart.getId());

        assertThat(expected).isSameAs(cart);

        verify(cartRepository).findById(cart.getId());
    }

}