package br.com.ecommerceapi.controller;

import br.com.ecommerceapi.dto.ProductRequestDTO;
import br.com.ecommerceapi.dto.ProductResponseDTO;
import br.com.ecommerceapi.entity.Product;
import br.com.ecommerceapi.entity.converter.Mapper;
import br.com.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid ProductRequestDTO productDTO) {
        Product product = productService.save(Mapper.map(productDTO, Product.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(Mapper.map(product, ProductResponseDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ProductRequestDTO productDTO) {
        Product product = productService.update(id, Mapper.map(productDTO, Product.class));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Mapper.map(product, ProductResponseDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(Mapper.mapAll(products, ProductResponseDTO.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(convertToDTO(product));
    }

    private ProductResponseDTO convertToDTO(Product product) {
        return Mapper.map(product, ProductResponseDTO.class);
    }

}
