package com.a00326153.rental.service;

import com.a00326153.rental.dto.RentalDto;
import java.util.List;

public interface RentalService {
    RentalDto createRental(RentalDto rentalDto);
    RentalDto getRentalById(Long id);
    List<RentalDto> getAllRentals();
    RentalDto updateRental(Long id, RentalDto rentalDto);
    void deleteRental(Long id);
    List<RentalDto> getRentalsByCustomerName(String name);
}
