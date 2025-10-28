package com.example.dproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderState status;

    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> items;

    @AttributeOverrides({
            @AttributeOverride(name="firstName",  column=@Column(name="ship_first_name")),
            @AttributeOverride(name="lastName",   column=@Column(name="ship_last_name")),
            @AttributeOverride(name="phone",      column=@Column(name="ship_phone")),
            @AttributeOverride(name="line1",      column=@Column(name="ship_line1")),
            @AttributeOverride(name="line2",      column=@Column(name="ship_line2")),
            @AttributeOverride(name="city",       column=@Column(name="ship_city")),
            @AttributeOverride(name="region",     column=@Column(name="ship_region")),
            @AttributeOverride(name="postalCode", column=@Column(name="ship_postal_code")),
            @AttributeOverride(name="countryCode",column=@Column(name="ship_country_code"))
    })
    @Embedded
    private AddressSnapshot shippingAddress;
}
