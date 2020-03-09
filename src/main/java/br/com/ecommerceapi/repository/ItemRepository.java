package br.com.ecommerceapi.repository;

import br.com.ecommerceapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
