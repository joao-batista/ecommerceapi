package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Cart;
import br.com.ecommerceapi.entity.Item;
import br.com.ecommerceapi.entity.Order;
import br.com.ecommerceapi.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

public interface CartService {

    Cart getCart(User user);

    void addToCart(Item item, User user);

    void delete(Long itemId, User user);

    void applyTicket(String code, User user);

    void checkout(User user);

}
