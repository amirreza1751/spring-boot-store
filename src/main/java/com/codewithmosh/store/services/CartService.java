package com.codewithmosh.store.services;

import com.codewithmosh.store.dtos.CartDto;
import com.codewithmosh.store.dtos.CartItemDto;
import com.codewithmosh.store.entities.Cart;
import com.codewithmosh.store.exceptions.CartNotfoundException;
import com.codewithmosh.store.exceptions.ProductNotFoundException;
import com.codewithmosh.store.mappers.CartMapper;
import com.codewithmosh.store.repositories.CartRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    private ProductRepository productRepository;

    public CartDto createCart(){
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    public CartItemDto addToCart(String cartId, Long productId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null){
            throw new CartNotfoundException();
        }

        var product = productRepository.findById(productId).orElse(null);
        if (product == null){
            throw new CartNotfoundException();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }

    public CartDto getCart(String id){
        var cart = cartRepository.getCartWithItems(id).orElse(null);
        if(cart == null){
            throw new CartNotfoundException();
        }
        return cartMapper.toDto(cart);
    }

    public CartItemDto updateItem(String cartId, Long productId, Integer quantity){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            throw new CartNotfoundException();
        }

        var cartItem = cart.getItem(productId);
        if(cartItem == null){
            throw new ProductNotFoundException();
        }
        cartItem.setQuantity(quantity);
        cartRepository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public void removeItem(String cartId, Long productId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            throw new CartNotfoundException();
        }
        cart.removeItem(productId);

        cartRepository.save(cart);
    }

    public void clearCart(String cartId){
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            throw new CartNotfoundException();
        }
        cart.clear();

        cartRepository.save(cart);
    }
}


