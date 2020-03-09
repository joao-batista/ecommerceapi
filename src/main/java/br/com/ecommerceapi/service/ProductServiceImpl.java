package br.com.ecommerceapi.service;

import br.com.ecommerceapi.entity.Product;
import br.com.ecommerceapi.entity.converter.Mapper;
import br.com.ecommerceapi.exception.ResourceNotFoundException;
import br.com.ecommerceapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Product id '" + id + "' does no exist"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productDB = findById(id);
        Product productMapped = Mapper.map(product, productDB);
        return productRepository.save(productMapped);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        this.productRepository.deleteById(id);

    }

    @Override
    public Product saveImage(MultipartFile file, Long id) {
        byte[] image = imageService.processImage(file);
        Product product = findById(id);
        product.setImage(image);
        return save(product);
    }
}
