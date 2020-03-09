package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Item;
import br.com.ecommerceapi.entity.User;

public interface ItemService {

    void update(Long id, Integer quantity, User user);

    Item findById(Long id);

    void save(Item product);

    void deleteById(Long id);
}
