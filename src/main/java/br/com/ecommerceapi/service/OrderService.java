package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(Long id);

    Order save(Order order);
}
