package org.manage.scms.service;

import org.manage.scms.dto.ProductDto;
import org.manage.scms.exception.ProductNotFoundException;
import org.manage.scms.model.Product;
import org.manage.scms.notifications.ProductAddedEvent;
import org.manage.scms.repository.ProductRepository;
import org.manage.scms.util.ProductUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final String EXCEPTION_EXPRESSION = "Product not found by ID: ";
    private final ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    public Product addProduct(Product product) {

        if (product != null) {
            ProductDto productDto = ProductUtil.convertProductToDto(product);
            ProductAddedEvent event = new ProductAddedEvent(this, product.getName(), productDto);
            eventPublisher.publishEvent(event);

            productRepository.save(product);
            return product;
        }
        return null;
    }

    public void deleteProducts() {
        productRepository.deleteAll();
    }

    public void deleteProductById(Long id) throws Exception {
        productRepository.findById(id)
                .orElseThrow(() -> new ExpressionException(EXCEPTION_EXPRESSION + id));
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ExpressionException(EXCEPTION_EXPRESSION + id));
    }

    public void updateProduct(ProductDto product, Long id) throws ProductNotFoundException, Exception {
        Product storedProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));

        if (storedProduct == null) throw new Exception("User not found");

        ProductUtil.entityToDtoSetter(storedProduct, product);

        productRepository.save(storedProduct);
    }
}
