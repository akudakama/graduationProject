package com.example.dproject.controller;

import com.example.dproject.dto.AddressDto;
import com.example.dproject.entity.User;
import com.example.dproject.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<AddressDto>> list(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(addressService.getMyAddresses(user.getId()));
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<AddressDto> create(@AuthenticationPrincipal User user,
                                             @Valid @RequestBody AddressDto dto) {
        return ResponseEntity.ok(addressService.create(user.getId(), dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<AddressDto> update(@AuthenticationPrincipal User user,
                                             @PathVariable Long id,
                                             @Valid @RequestBody AddressDto dto) {
        return ResponseEntity.ok(addressService.update(user.getId(), id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user,
                                       @PathVariable Long id) {
        addressService.delete(user.getId(), id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/default")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<AddressDto> makeDefault(@AuthenticationPrincipal User user,
                                                  @PathVariable Long id) {
        return ResponseEntity.ok(addressService.makeDefault(user.getId(), id));
    }
}
