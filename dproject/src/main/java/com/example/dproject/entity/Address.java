package com.example.dproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    private String firstName;
    private String lastName;
    private String phone;

    private String line1;
    private String line2;
    private String city;
    private String region;

    private String postalCode;
    private String countryCode;

    private boolean isDefault;
}
