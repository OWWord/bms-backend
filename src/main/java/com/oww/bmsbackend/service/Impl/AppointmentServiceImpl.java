package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Appointment;
import com.oww.bmsbackend.repository.AppointmentRepository;
import com.oww.bmsbackend.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentServiceImpl(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Appointment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Appointment> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
