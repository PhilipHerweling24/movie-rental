package com.a00326153.rental.serviceImpl;

import com.a00326153.rental.client.MovieClient;
import com.a00326153.rental.dto.MovieDto;
import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.entity.Rental;
import com.a00326153.rental.exception.ResourceNotFoundException;
import com.a00326153.rental.mapper.RentalMapper;
import com.a00326153.rental.repository.RentalRepository;
import com.a00326153.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final MovieClient movieClient;
    private final RentalMapper rentalMapper;

    @Override
    public RentalDto createRental(RentalDto rentalDto) {
        Rental rental = rentalMapper.toEntity(rentalDto);
        Rental saved = rentalRepository.save(rental);

        MovieDto movie = movieClient.getMovieById(saved.getMovieId());
        RentalDto dto = rentalMapper.toDto(saved);
        dto.setMovie(movie);
        return dto;
    }

    @Override
    public RentalDto getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        MovieDto movie = movieClient.getMovieById(rental.getMovieId());

        RentalDto dto = rentalMapper.toDto(rental);
        dto.setMovie(movie);
        return dto;
    }

    @Override
    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll().stream().map(rental -> {
            RentalDto dto = rentalMapper.toDto(rental);
            dto.setMovie(movieClient.getMovieById(rental.getMovieId()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RentalDto updateRental(Long id, RentalDto rentalDto) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", id));

        rental.setCustomerName(rentalDto.getCustomerName());
        rental.setMovieId(rentalDto.getMovieId());
        rental.setRentalDate(rentalDto.getRentalDate());

        Rental updated = rentalRepository.save(rental);

        MovieDto movieDto = movieClient.getMovieById(updated.getMovieId());

        RentalDto response = rentalMapper.toDto(updated);
        response.setMovie(movieDto);
        return response;
    }

    @Override
    public void deleteRental(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental", "id", id));
        rentalRepository.delete(rental);
    }

    @Override
    public List<RentalDto> getRentalsByCustomerName(String name) {
        List<Rental> rentals = rentalRepository.findByCustomerName(name);
        return rentals.stream().map(rental -> {
            RentalDto dto = rentalMapper.toDto(rental);
            dto.setMovie(movieClient.getMovieById(rental.getMovieId()));
            return dto;
        }).collect(Collectors.toList());
    }
}
