package com.oww.bmsbackend.repository;

import com.oww.bmsbackend.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Integer> {
}
