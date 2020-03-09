package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Item;
import br.com.ecommerceapi.entity.User;
import br.com.ecommerceapi.exception.ResourceNotFoundException;
import br.com.ecommerceapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    @Transactional
    public void update(Long id, Integer quantity, User user) {
        Optional<Item> optional = user.getCart().getItems().stream().filter(e -> id == (e.getId())).findFirst();
        optional.ifPresent(item -> {
            item.setQuantity(quantity);
            itemRepository.save(item);
        });
    }

    @Override
    public Item findById(Long id) {
        Item item = this.itemRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Item id '" + id + "' does no exist"));
        return item;
    }

    @Override
    public void save(Item product) {
        itemRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
