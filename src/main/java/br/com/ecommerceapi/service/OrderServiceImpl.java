package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Order;
import br.com.ecommerceapi.exception.ResourceNotFoundException;
import br.com.ecommerceapi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Order id '" + id + "' does no exist"));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }


}
