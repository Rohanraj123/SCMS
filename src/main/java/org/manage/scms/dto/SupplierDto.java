package org.manage.scms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.manage.scms.model.Product;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private String name;
    private BigDecimal phoneNumber;
    private String address;
    private String email;
    private Set<Product> products;
}
