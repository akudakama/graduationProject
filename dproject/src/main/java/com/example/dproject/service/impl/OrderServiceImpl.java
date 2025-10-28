package com.example.dproject.service.impl;

import com.example.dproject.dto.OrderDto;
import com.example.dproject.entity.*;
import com.example.dproject.mapper.OrderMapper;
import com.example.dproject.repository.*;
import com.example.dproject.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
<<<<<<< HEAD
import java.util.Objects;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
<<<<<<< HEAD
    private final AddressRepository addressRepository;
=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(OrderMapper::toDto).toList();
    }


    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        try {
            OrderState newStatus = OrderState.valueOf(status.toUpperCase());
            order.setStatus(newStatus);
            orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::toDto).toList();
    }


    @Override
    @Transactional
<<<<<<< HEAD
    public OrderDto placeOrder(Long userId, Long addressId) {
=======
    public OrderDto placeOrder(Long userId) {
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cart not found for user id: " + userId));

        List<CartItem> cartItems = Optional.ofNullable(cart.getItems()).orElse(List.of());
<<<<<<< HEAD
=======

>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        if (cartItems.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cart is empty");
        }

<<<<<<< HEAD
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid address id: " + addressId));
        if (address.getUser() == null || !Objects.equals(address.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Address does not belong to the user");
        }


=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderState.NEW);

<<<<<<< HEAD
        AddressSnapshot snap = new AddressSnapshot();
        snap.setFirstName(address.getFirstName());
        snap.setLastName(address.getLastName());
        snap.setPhone(address.getPhone());
        snap.setLine1(address.getLine1());
        snap.setLine2(address.getLine2());
        snap.setCity(address.getCity());
        snap.setRegion(address.getRegion());
        snap.setPostalCode(address.getPostalCode());
        snap.setCountryCode(address.getCountryCode());
        order.setShippingAddress(snap);

=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPriceAtPurchase(cartItem.getProduct().getPrice());
            return item;
        }).toList();
<<<<<<< HEAD
        order.setItems(orderItems);

        BigDecimal total = orderItems.stream()
                .map(i -> i.getPriceAtPurchase().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
=======

        order.setItems(orderItems);


        BigDecimal total = orderItems.stream()
                .map(i -> i.getPriceAtPurchase().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
        order.setTotalAmount(total);

        cart.getItems().clear();
        cartRepository.save(cart);

        orderRepository.save(order);

        return OrderMapper.toDto(order);
    }
<<<<<<< HEAD

=======
>>>>>>> 2eb0b70457c845a808fdf1aa218e14ca4e36ef7d
}
