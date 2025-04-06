package com.a00326153.rental.client;

import com.a00326153.rental.dto.MovieDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieClient {

    private final RestTemplate restTemplate;
    private final String movieServiceBaseUrl;

    public MovieClient(RestTemplate restTemplate,
                       @Value("${movie.service.url}") String movieServiceBaseUrl) {
        this.restTemplate = restTemplate;
        this.movieServiceBaseUrl = movieServiceBaseUrl;
    }

    public MovieDto getMovieById(Long movieId) {
        return restTemplate.getForObject(movieServiceBaseUrl + "/movies/" + movieId, MovieDto.class);
    }
}