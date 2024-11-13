package org.manage.scms.util;

import org.manage.scms.dto.ProductDto;
import org.manage.scms.model.Product;

public class ProductUtil
{
    public static Product convertDtoToProduct(ProductDto productDto)
    {
        Product product = new Product();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setSupplier(productDto.getSupplier());

        return product;
    }
}
