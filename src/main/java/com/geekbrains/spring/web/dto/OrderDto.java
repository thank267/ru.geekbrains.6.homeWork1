package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private String phone;
    private String address;

    public OrderDto(Order order) {
        this.phone = order.getPhone();
        this.address = order.getAddress();

    }
}
