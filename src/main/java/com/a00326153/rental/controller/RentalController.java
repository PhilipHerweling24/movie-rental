package com.a00326153.rental.controller;

import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.dto.ResponseDto;
import com.a00326153.rental.entity.Rental;
import com.a00326153.rental.serviceImpl.RentalServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalServiceImpl rentalService;

    public RentalController(RentalServiceImpl rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalDto>> getAllRentals() {
        return ResponseEntity.ok(rentalService.findAllRentals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.findRentalById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createRental(@RequestBody Rental rental) {
        rentalService.saveRental(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Rental Created Successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateRental(@PathVariable Long id, @RequestBody RentalDto rental) {
        rentalService.updateRental(id, rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Rental Updated Successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto("201", "Rental Deleted Successfully"));
    }

    @GetMapping("/customer")
    public ResponseEntity<List<RentalDto>> findRentalsByCustomer(@RequestParam String name) {
        return ResponseEntity.ok(rentalService.findRentalsByCustomerName(name));
    }
}
