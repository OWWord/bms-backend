package com.oww.bmsbackend.service;

import com.oww.bmsbackend.entity.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> findAll();

    Appointment findById(int id);

    Appointment createAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    void deleteById(int id);

}
