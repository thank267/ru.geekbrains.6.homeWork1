package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.*;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.entities.User;
import com.geekbrains.spring.web.exceptions.AppError;
import com.geekbrains.spring.web.services.CartService;
import com.geekbrains.spring.web.services.OrderService;
import com.geekbrains.spring.web.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final UserService userService;
    private final OrderService orderService;


    @PostMapping
    public void saveNewOrder(Principal principal, @RequestBody OrderDto orderDto) {

       User user = userService.findByUsername(principal.getName()).orElseThrow();

        orderService.addOrder(user, orderDto);

    }
}
