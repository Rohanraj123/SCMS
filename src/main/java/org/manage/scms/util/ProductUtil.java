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

    public static ProductDto convertProductToDto(Product product)
    {
        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setSupplier(product.getSupplier());
        productDto.setQuantity(product.getQuantity());
        productDto.setPrice(product.getPrice());

        return productDto;
    }

    public static void entityToDtoSetter(Product entity, ProductDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setSupplier(dto.getSupplier());
    }
}
