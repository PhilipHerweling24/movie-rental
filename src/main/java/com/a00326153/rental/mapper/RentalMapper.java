package com.a00326153.rental.mapper;

import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.entity.Rental;

public class RentalMapper {

    private RentalMapper() {}

    public static RentalDto mapToRentalDto(Rental rental) {
        RentalDto dto = new RentalDto();
        dto.setId(rental.getId());
        dto.setCustomerName(rental.getCustomerName());
        dto.setMovieTitle(rental.getMovieTitle());
        dto.setRentalDate(rental.getRentalDate());
        return dto;
    }
}
