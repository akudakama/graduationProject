package com.example.dproject.service.impl;

import com.example.dproject.dto.AddressDto;
import com.example.dproject.entity.Address;
import com.example.dproject.entity.User;
import com.example.dproject.repository.AddressRepository;
import com.example.dproject.repository.UserRepository;
import com.example.dproject.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AddressDto> getMyAddresses(Long userId) {
        return addressRepository.findByUserId(userId)
                .stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public AddressDto create(Long userId, AddressDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Address a = new Address();
        a.setUser(user);
        apply(a, dto);

        if (Boolean.TRUE.equals(dto.getIsDefault())) {
            addressRepository.findByUserIdAndIsDefaultTrue(userId)
                    .ifPresent(prev -> prev.setDefault(false));
            a.setDefault(true);
        }
        return toDto(addressRepository.save(a));
    }

    @Override
    @Transactional
    public AddressDto update(Long userId, Long addressId, AddressDto dto) {
        Address a = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + addressId));

        apply(a, dto);

        if (dto.getIsDefault() != null) {
            if (dto.getIsDefault()) {
                addressRepository.findByUserIdAndIsDefaultTrue(userId)
                        .ifPresent(prev -> { if (!prev.getId().equals(a.getId())) prev.setDefault(false); });
                a.setDefault(true);
            } else {
                a.setDefault(false);
            }
        }
        return toDto(a);
    }

    @Override
    @Transactional
    public void delete(Long userId, Long addressId) {
        Address a = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + addressId));

        boolean wasDefault = a.isDefault();
        addressRepository.delete(a);

        if (wasDefault) {
            addressRepository.findByUserId(userId).stream().findFirst()
                    .ifPresent(any -> any.setDefault(true));
        }
    }

    @Override
    @Transactional
    public AddressDto makeDefault(Long userId, Long addressId) {
        Address a = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + addressId));
        addressRepository.findByUserIdAndIsDefaultTrue(userId)
                .ifPresent(prev -> { if (!prev.getId().equals(a.getId())) prev.setDefault(false); });
        a.setDefault(true);
        return toDto(a);
    }

    private void apply(Address a, AddressDto d) {
        if (d.getFirstName() != null)  a.setFirstName(d.getFirstName());
        if (d.getLastName()  != null)  a.setLastName(d.getLastName());
        if (d.getPhone()     != null)  a.setPhone(d.getPhone());
        if (d.getLine1()     != null)  a.setLine1(d.getLine1());
        if (d.getLine2()     != null)  a.setLine2(d.getLine2());
        if (d.getCity()      != null)  a.setCity(d.getCity());
        if (d.getRegion()    != null)  a.setRegion(d.getRegion());
        if (d.getPostalCode()!= null)  a.setPostalCode(d.getPostalCode());
        if (d.getCountryCode()!= null) a.setCountryCode(d.getCountryCode().toUpperCase());
    }

    private AddressDto toDto(Address a) {
        AddressDto d = new AddressDto();
        d.setId(a.getId());
        d.setFirstName(a.getFirstName());
        d.setLastName(a.getLastName());
        d.setPhone(a.getPhone());
        d.setLine1(a.getLine1());
        d.setLine2(a.getLine2());
        d.setCity(a.getCity());
        d.setRegion(a.getRegion());
        d.setPostalCode(a.getPostalCode());
        d.setCountryCode(a.getCountryCode());
        d.setIsDefault(a.isDefault());
        return d;
    }
}
