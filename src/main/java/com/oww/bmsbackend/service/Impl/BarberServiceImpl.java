package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.exception.NotAcceptableException;
import com.oww.bmsbackend.exception.NotFoundException;
import com.oww.bmsbackend.repository.BarberRepository;
import com.oww.bmsbackend.service.BarberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberServiceImpl implements BarberService {

    private final BarberRepository barberRepository;

    public BarberServiceImpl(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    @Override
    public List<Barber> findAll() {
        List<Barber> barbers = barberRepository.findAll();
        if (barbers.isEmpty()) throw new NotFoundException("Not Found: Barbers is not found");
        return barberRepository.findAll();
    }

    @Override
    public Barber findById(int id) {
        return barberRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Not Found: Barber by id = %d is not found", id)));
    }

    @Override
    public Barber createBarber(Barber barber) {
        if (barber.getId() != 0) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must be 0 to create barber this id = %d", barber.getId()));
        }
        return barberRepository.save(barber);
    }

    @Override
    public Barber updateBarber(Barber barber) {
        Optional<Barber> optionalBarber = barberRepository.findById(barber.getId());
        if (!optionalBarber.isPresent()) {
            throw new NotAcceptableException("Not Acceptable: Trying to update non-existent a barber");
        }
        if (barber.getId() == 0) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must not be 0 to create barber this id = %d", barber.getId()));
        }
        return barberRepository.save(barber);
    }

    @Override
    public void deleteById(int id) {
        Optional<Barber> optionalBarber = barberRepository.findById(id);
        if (!optionalBarber.isPresent())
            throw new NotFoundException(String.format("Not Found: Barber by id = %d is not found", id));
        else
            barberRepository.deleteById(id);
    }
}
