package com.oww.bmsbackend.service;

import com.oww.bmsbackend.entity.Barber;

import java.util.List;
import java.util.Optional;

public interface BarberService {

    List<Barber> findAll();

    Optional<Barber> findById(int id);

    Barber createBarber(Barber barber);

    Barber updateBarber(Barber barber);

    void deleteById(int id);

}
