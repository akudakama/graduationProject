// src/main/java/com/example/dproject/dto/AddressDto.java
package com.example.dproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDto {
    private Long id;

    @NotBlank @Size(max = 100)
    private String firstName;

    @NotBlank @Size(max = 100)
    private String lastName;

    @Size(max = 32)
    @Pattern(regexp = "^[+\\d][\\d\\s-]{6,30}$", message = "Invalid phone format")
    private String phone;

    @NotBlank @Size(max = 255)
    private String line1;

    @Size(max = 255)
    private String line2;

    @NotBlank @Size(max = 120)
    private String city;

    @Size(max = 120)
    private String region;

    @NotBlank @Size(max = 32)
    private String postalCode;

    @Size(min = 2, max = 2)
    @Pattern(regexp = "UA", message = "Country must be UA")
    private String countryCode;

    private Boolean isDefault;
}
