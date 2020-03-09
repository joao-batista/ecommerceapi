package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(long id);

    Product save(Product order);

    Product update(Long id, Product car);

    void deleteById(Long id);

    Product saveImage(MultipartFile file, Long id);
}
