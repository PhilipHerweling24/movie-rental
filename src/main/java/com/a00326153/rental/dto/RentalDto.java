package com.a00326153.rental.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class RentalDto extends RepresentationModel<RentalDto> {
    private Long id;
    private String customerName;
    private String movieTitle;
    private String rentalDate;
}
