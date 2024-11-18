package org.manage.scms.controller;

import org.manage.scms.dto.ProductDto;
import org.manage.scms.service.ProductService;
import org.manage.scms.model.Product;
import org.manage.scms.util.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto)
    {
        try {
            if (AuthUtil.hasRole("ADMIN")) {
                Product product = convertDtoToProduct(productDto);
                return ResponseEntity.ok(productService.addProduct(product));
            }
            return ResponseEntity.badRequest().body(null);
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/deleteProducts")
    public ResponseEntity<String> deleteProducts(@RequestParam Boolean status)
    {
        if (status)
        {
            productService.deleteProducts();
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.badRequest().body("Unexpected error occurred!");
    }

    @PostMapping("/deleteProductById")
    public ResponseEntity<String> deleteProductById(@RequestParam Long id)
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
