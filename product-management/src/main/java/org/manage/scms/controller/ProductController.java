package org.manage.scms.controller;

import org.manage.scms.dto.ProductDto;
import org.manage.scms.service.ProductService;
import org.manage.scms.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.manage.scms.util.ProductUtil.convertDtoToProduct;

@RestController
@RequestMapping("/admin/product/")
public class ProductController
{
    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto)
    {
        try {
            Product product = convertDtoToProduct(productDto);
            return ResponseEntity.ok(productService.addProduct(product));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/deleteProductById")
    public ResponseEntity<String> deleteProduct(@RequestParam Long id)
    {
        try
        {
            productService.deleteProductById(id);
            return ResponseEntity.ok("Product with id: " + id + " deleted successfully!");
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        try
        {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/getProductById")
    public ResponseEntity<Product> getProductById(@RequestParam Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
