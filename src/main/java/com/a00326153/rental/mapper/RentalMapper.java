package com.a00326153.rental.mapper;

import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.entity.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    public RentalDto toDto(Rental rental) {
        RentalDto dto = new RentalDto();
        dto.setId(rental.getId());
        dto.setCustomerName(rental.getCustomerName());
        dto.setRentalDate(rental.getRentalDate());
        dto.setMovieId(rental.getMovieId());
        return dto;
    }

    public Rental toEntity(RentalDto dto) {
        Rental.RentalBuilder builder = Rental.builder()
                .customerName(dto.getCustomerName())
                .movieId(dto.getMovieId())
                .rentalDate(dto.getRentalDate());

        if (dto.getId() != null) {
            builder.id(dto.getId());
        }

        return builder.build();
    }
}