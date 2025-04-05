package com.a00326153.rental.service;

import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.entity.Rental;

import java.util.List;

public interface RentalService {
    List<RentalDto> findAllRentals();
    RentalDto findRentalById(Long id);
    RentalDto saveRental(Rental rental);
    RentalDto updateRental(Long id, RentalDto dto);
    void deleteRental(Long id);
    List<RentalDto> findRentalsByCustomerName(String name);
}
