package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Appointment;
import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.exception.NotAcceptableException;
import com.oww.bmsbackend.exception.NotFoundException;
import com.oww.bmsbackend.repository.AppointmentRepository;
import com.oww.bmsbackend.repository.BarberRepository;
import com.oww.bmsbackend.service.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final BarberRepository barberRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, BarberRepository barberRepository) {
        this.appointmentRepository = appointmentRepository;
        this.barberRepository = barberRepository;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        if (appointments.isEmpty()) throw new NotFoundException("Not Found: Appointments is not found");
        return appointments;
    }

    @Override
    public Appointment findById(int id) {
        return appointmentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Not Found: Appointment by id = %d is not found", id)));
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getId() != 0) {
            throw new NotAcceptableException(String.format(
                    "Not Acceptable: ID must be 0 to create appointment this id = %d", appointment.getId()));
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Optional<Barber> optionalBarber = barberRepository.findById(appointment.getBarber().getId());
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
        if (!optionalBarber.isPresent()) {
            throw new NotAcceptableException("Not Acceptable: Trying to update a appointment without a optionalBarber");
        }
        if (!optionalAppointment.isPresent()) {
            throw new NotAcceptableException("Not Acceptable: Trying to update non-existent a appointment");
        }
        if (appointment.getId() == 0) {
            throw new NotAcceptableException(String.format(
                    "Not Acceptable: ID must not be 0 to create appointment this id = %d", appointment.getId()));
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteById(int id) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (!optionalAppointment.isPresent())
            throw new NotFoundException("Not Found: Appointment by id = %d is not found");
        else
            appointmentRepository.deleteById(id);
    }
}
