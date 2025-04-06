package com.a00326153.rental.dto;

import lombok.Data;

@Data
public class MovieDto {
    private long id;
    private String title;
    private String director;
    private int releaseDate;
}
