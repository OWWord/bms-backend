package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.repository.BarberRepository;
import com.oww.bmsbackend.service.BarberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberServiceImpl implements BarberService {

    private final BarberRepository repository;

    public BarberServiceImpl(BarberRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Barber> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Barber> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Barber createBarber(Barber barber) {
        return repository.save(barber);
    }

    @Override
    public Barber updateBarber(Barber barber) {
        return repository.save(barber);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
