package org.manage.scms.service;

import org.manage.scms.model.Product;
import org.manage.scms.repository.ProductRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService
{
    private final String EXCEPTION_EXPRESSION = "Product not found by ID: ";
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product)
    {
        if (product != null)
        {
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProducts()
    {
        productRepository.deleteAll();
    }

    public void deleteProductById(Long id) throws Exception
    {
        productRepository.findById(id)
                .orElseThrow(() -> new ExpressionException(EXCEPTION_EXPRESSION + id));
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product getProductById(Long id)
    {
        return productRepository.findById(id)
                .orElseThrow(() -> new ExpressionException(EXCEPTION_EXPRESSION + id));
    }
}
