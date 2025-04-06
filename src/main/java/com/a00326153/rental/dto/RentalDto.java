package com.a00326153.rental.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RentalDto {
    private Long id;
    private String customerName;
    private Long movieId;
    private LocalDate rentalDate;
    private MovieDto movie;
}
