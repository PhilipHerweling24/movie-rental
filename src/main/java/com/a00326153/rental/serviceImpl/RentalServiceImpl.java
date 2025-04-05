package com.a00326153.rental.serviceImpl;

import com.a00326153.rental.dto.RentalDto;
import com.a00326153.rental.entity.Rental;
import com.a00326153.rental.mapper.RentalMapper;
import com.a00326153.rental.repository.RentalRepository;
import com.a00326153.rental.service.RentalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDto> findAllRentals() {
        return rentalRepository.findAll().stream().map(RentalMapper::mapToRentalDto).toList();
    }

    public RentalDto findRentalById(Long id) {
        return RentalMapper.mapToRentalDto(rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found with id: "+id)));
    }

    public RentalDto saveRental(Rental rental) {
        validateRental(rental);
        return RentalMapper.mapToRentalDto(rentalRepository.save(rental));
    }

    public RentalDto updateRental(Long id, RentalDto updatedDto) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found with id: "+id));
        rental.setCustomerName(updatedDto.getCustomerName());
        rental.setMovieTitle(updatedDto.getMovieTitle());
        rental.setRentalDate(updatedDto.getRentalDate());
        return RentalMapper.mapToRentalDto(rentalRepository.save(rental));
    }

    public void deleteRental(Long id) {
        if (!rentalRepository.existsById(id)) {
            throw new IllegalArgumentException("Rental with ID " + id + " not found, cannot delete");
        }
        rentalRepository.deleteById(id);
    }

    public List<RentalDto> findRentalsByCustomerName(String name) {
        return rentalRepository.findByCustomerName(name).stream().map(RentalMapper::mapToRentalDto).toList();
    }

    private void validateRental(Rental rental) {
        if (rental == null) {
            throw new IllegalArgumentException("Cannot save null rental");
        }
        if (rental.getCustomerName() == null || rental.getCustomerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        if (rental.getMovieTitle() == null || rental.getMovieTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Movie title cannot be blank");
        }
        if (rental.getRentalDate() == null || rental.getRentalDate().trim().isEmpty()) {
            throw new IllegalArgumentException("Rental date cannot be blank");
        }
    }
}
