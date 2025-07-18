package com.example.dproject.dto;

import com.example.dproject.entity.OrderState;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {

    private OrderState status;

}
