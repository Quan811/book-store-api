package com.myweb.controller;

import com.myweb.dto.ApiResponse;
import com.myweb.dto.CartDTO;
import com.myweb.entity.Cart;
import com.myweb.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("")
    ApiResponse<List<Cart>> getAllCarts(){
        return ApiResponse.<List<Cart>>builder()
                .success(true)
                .result(cartService.getAllCart())
                .build();

    }

    @GetMapping("/{cartId}")
    ApiResponse<Cart> getCartById(@PathVariable Long cartId){
        return  ApiResponse.<Cart>builder()
                .success(true)
                .result(cartService.getCartById(cartId))
                .build();
    }

    @PostMapping("/new-cart")
    ApiResponse<Cart> createCart(@RequestBody CartDTO cartDTO){
        return  ApiResponse.<Cart>builder()
                .success(true)
                .result(cartService.createCart(cartDTO))
                .build();
    }

    @PutMapping("/update/{cartId}")
    ApiResponse<Cart> updateCart(@PathVariable Long cartId, @RequestBody CartDTO cartDTO){
        return ApiResponse.<Cart>builder()
                .success(true)
                .result(cartService.updateCart(cartId, cartDTO))
                .build();
    }

    @DeleteMapping("/{cartId}")
    ApiResponse<Cart> deleteCart(@PathVariable Long cartId ){
        cartService.deleteCart(cartId);
        return ApiResponse.<Cart>builder()
                .success(true)
                .message("Delete cart successfully")
                .build();
    }
}
