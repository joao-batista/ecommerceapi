package br.com.ecommerceapi.controller;

import br.com.ecommerceapi.dto.CartDTO;
import br.com.ecommerceapi.dto.ItemRequestDTO;
import br.com.ecommerceapi.entity.Cart;
import br.com.ecommerceapi.entity.Item;
import br.com.ecommerceapi.entity.Product;
import br.com.ecommerceapi.entity.User;
import br.com.ecommerceapi.entity.converter.Mapper;
import br.com.ecommerceapi.security.AuthService;
import br.com.ecommerceapi.service.CartService;
import br.com.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthService authService;

    @GetMapping("")
    public ResponseEntity<CartDTO> getCart() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(convertToDTO(cartService.getCart(user)));
    }

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addToCart(@RequestBody ItemRequestDTO item) {
        User user = authService.getCurrentUser();
        Product product = productService.findById(item.getProductId());
        cartService.addToCart(new Item(product, item.getQuantity()), user);
        return ResponseEntity.ok(convertToDTO(cartService.getCart(user)));
    }


    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        User user = authService.getCurrentUser();
        cartService.delete(id, user);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout() {
        User user = authService.getCurrentUser();
        cartService.checkout(user);
        return ResponseEntity.ok(null);
    }


    private CartDTO convertToDTO(Cart cart) {
        return Mapper.map(cart, CartDTO.class);
    }

}
