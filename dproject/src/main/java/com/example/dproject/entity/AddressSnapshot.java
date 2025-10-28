package com.example.dproject.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter; import lombok.Setter;

@Embeddable @Getter @Setter
public class AddressSnapshot {
    private String firstName, lastName, phone;
    private String line1, line2, city, region;
    private String postalCode, countryCode;
}