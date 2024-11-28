package org.manage.scms.util;

import org.manage.scms.dto.SupplierDto;
import org.manage.scms.model.Supplier;

public class SupplierUtil {
    public static Supplier convertSupplierDtoToEntity(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();

        supplier.setName(supplierDto.getName());
        supplier.setEmail(supplierDto.getEmail());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setProducts(supplierDto.getProducts());
        supplier.setAddress(supplierDto.getAddress());

        return supplier;
    }
}
