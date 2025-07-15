package com.example.dproject.service.impl;

import com.example.dproject.entity.*;
import com.example.dproject.repository.*;
import com.example.dproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order placeOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        List<CartItem> cartItems = cart.getItems();

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderState.NEW);

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPriceAtPurchase(cartItem.getProduct().getPrice());
            return item;
        }).toList();

        order.setItems(orderItems);
        order.setTotalAmount(
                orderItems.stream()
                        .mapToInt(i -> (int) (i.getQuantity() * i.getPriceAtPurchase()))
                        .sum()
        );

        cartItemRepository.deleteAll(cartItems);
        return orderRepository.save(order);
    }
}
