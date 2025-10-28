package com.example.dproject.service;

import com.example.dproject.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getMyAddresses(Long userId);
    AddressDto create(Long userId, AddressDto dto);
    AddressDto update(Long userId, Long addressId, AddressDto dto);
    void delete(Long userId, Long addressId);
    AddressDto makeDefault(Long userId, Long addressId);
}
