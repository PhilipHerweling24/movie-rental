package com.a00326153.rental.controller;

import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.dto.ResponseDto;
import com.a00326153.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        return ResponseEntity.ok(rentalService.createRental(rentalDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateRental(@PathVariable Long id, @RequestBody RentalDto rentalDto) {
        RentalDto updatedRental = rentalService.updateRental(id, rentalDto);
        return new ResponseEntity<>(new ResponseDto("Rental updated", HttpStatus.OK.value(), updatedRental), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return new ResponseEntity<>(new ResponseDto("Rental deleted", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/customer/{name}")
    public ResponseEntity<ResponseDto> getRentalsByCustomerName(@PathVariable String name) {
        List<RentalDto> rentals = rentalService.getRentalsByCustomerName(name);
        return new ResponseEntity<>(new ResponseDto("Rentals fetched", HttpStatus.OK.value(), rentals), HttpStatus.OK);
    }
}
