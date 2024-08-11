package com.myweb.service;
import com.myweb.dto.CartDTO;
import com.myweb.entity.Cart;
import com.myweb.mapper.CartMapper;
import com.myweb.mapper.UserMapper;
import com.myweb.repository.CartRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public CartMapper cartMapper;
    @Autowired
    public EntityManager entityManager;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public Cart getCartById(Long cartId){
        return cartRepository.findById(cartId)
                .orElseThrow(()-> new RuntimeException("Cart not found"));
    }

    public Cart createCart(CartDTO cartDTO){
        Cart cart = cartMapper.toCart(cartDTO);
        return cartRepository.save(cart);
    }

    public Cart updateCart(Long cartId, CartDTO cartDTO){
        Cart cart = getCartById(cartId);
        cartMapper.updateCart(cartDTO, cart);
        return cartRepository.save(cart);
    }

    public void deleteCart(Long cartId){
        if(cartRepository.findById(cartId).isPresent()){
            cartRepository.deleteById(cartId);
        }
        else{
            throw new RuntimeException("Cart not found");
        }
    }

}
