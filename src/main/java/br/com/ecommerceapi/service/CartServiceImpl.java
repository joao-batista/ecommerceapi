package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.*;
import br.com.ecommerceapi.exception.ResourceNotFoundException;
import br.com.ecommerceapi.repository.CartRepository;
import br.com.ecommerceapi.repository.ItemRepository;
import br.com.ecommerceapi.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    ItemService itemService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TicketService ticketService;

    @Override
    public Cart getCart(User user) {
        return user.getCart();
    }


    @Override
    @Transactional
    public void addToCart(Item item, User user) {
        try {
            Cart cart = user.getCart();
            Set<Item> itemsCart = cart.getItems();
            Optional<Item> itemCart = itemsCart.stream().filter(
                    i -> i.getProduct().getId().equals(item.getProduct().getId())).findFirst();
            Item product;
            if (itemCart.isPresent()) {
                product = itemCart.get();
                product.setQuantity(item.getQuantity() + product.getQuantity());
            } else {
                product = item;
                product.setCart(cart);
                cart.getItems().add(product);
            }
            itemService.save(product);
            cartRepository.save(cart);

        } catch (Exception e) {
            log.error("ERROR: " + e.getMessage());
            log.error("CAUSE: " + e.getCause());
        }
    }

    @Override
    @Transactional
    public void delete(Long id, User user) {
        try {
            Item item = itemService.findById(id);
            item.setCart(null);
            item.setProduct(null);
            itemService.deleteById(item.getId());

        } catch (Exception e) {
            log.error("ERROR: " + e.getMessage());
            log.error("CAUSE: " + e.getCause());
        }
    }

    @Override
    public void applyTicket(String code, User user) {
        Ticket ticket = ticketService.findByCode(code);
        Cart cart = user.getCart();

        double total = cart.getTotalDiscount();
        double totalDiscount = ticket.getDiscount() * total;
        double totalDiscountTicket = cart.getTotalDiscountTicket();

        if(totalDiscountTicket == 0) { // first ticket
            cart.setTotalDiscountTicket(totalDiscount);
        }

        if(totalDiscount < totalDiscountTicket) {
            cart.setTotalDiscountTicket(totalDiscount);
        }

    }

    @Override
    @Transactional
    public void checkout(User user) {
        Order order = new Order(user);
        order.setTotalPrice(user.getCart().getTotalDiscount());
        orderRepository.save(order);

        user.getCart().getItems().forEach(item -> {
            item.setCart(null);
            item.setOrder(order);
            itemService.save(item);
        });
    }
}
