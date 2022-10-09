package com.oww.bmsbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "appointment", schema = "haircutbookingsystem")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Appointment {

    private static final String EMPTY_STRING = "";
    private static final int EMPTY_INT = 0;
    private static final Calendar EMPTY_CALENDAR = new GregorianCalendar(2000, 0, 01);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "client_name", nullable = false)
    private String clientName;
    @Column(name = "visit_date", nullable = false)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm",
            locale = "en_GB")
    private Calendar visitDate;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    public int getId() {
        return Optional.ofNullable(id).orElse(EMPTY_INT);
    }

    public String getClientName() {
        return Optional.ofNullable(clientName).orElse(EMPTY_STRING);
    }

    public Calendar getVisitDate() {
        return Optional.ofNullable(visitDate).orElse(EMPTY_CALENDAR);
    }

    public String getPhoneNumber() {
        return Optional.ofNullable(phoneNumber).orElse(EMPTY_STRING);
    }

    public Barber getBarber() {
        return barber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
