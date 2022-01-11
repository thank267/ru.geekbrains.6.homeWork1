package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.dto.OrderItemDto;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.entities.OrderItem;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.entities.User;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.OrderItemRepository;
import com.geekbrains.spring.web.repositories.OrderRepository;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import com.geekbrains.spring.web.repositories.specifications.ProductsSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductsService productService;

    @Transactional
    public void addOrder(User user, OrderDto orderDto) {

        if (!cartService.isEmpty()) {

            Cart cart = cartService.getCurrentCart();

            Order order = new Order();
            order.setUser(user);
            order.setTotal(cart.getTotalPrice());
            order.setPhone(orderDto.getPhone());
            order.setAddress(orderDto.getAddress());
            orderRepository.save(order);

            cart.getItems().forEach(ordeItemDto-> {
                OrderItem orderItem = new OrderItem();
                orderItem.setPrice(ordeItemDto.getPrice());
                orderItem.setPricePerProduct(ordeItemDto.getPricePerProduct());
                orderItem.setQuantity(ordeItemDto.getQuantity());
                orderItem.setProduct(productService.findById(ordeItemDto.getProductId()).orElseThrow());
                orderItem.setOrder(order);
                orderItemRepository.save(orderItem);
            });

            cart.clear();

        }

    }
}
