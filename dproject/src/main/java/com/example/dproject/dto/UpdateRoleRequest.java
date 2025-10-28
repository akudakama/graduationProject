package com.example.dproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRoleRequest {

    @NotBlank(message = "Role must not be blank")
    private String role;
}
