package org.manage.scms.notifications;

import lombok.Getter;
import lombok.Setter;
import org.manage.scms.dto.ProductDto;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class ProductAddedEvent extends ApplicationEvent {
    private final Object productDto;
    private final String productName;
    public ProductAddedEvent(Object source, String productName, ProductDto productDto) {
        super(source);
        this.productDto = productDto;
        this.productName = productName;
    }
}
