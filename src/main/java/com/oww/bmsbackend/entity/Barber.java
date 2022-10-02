package com.oww.bmsbackend.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "barbers", schema = "haircutbookingsystem")
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Barber {

    private static final String EMPTY_STRING = "";
    private static final int EMPTY_DEFAULT_ID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public int getId() {
        return Optional.ofNullable(id).orElse(EMPTY_DEFAULT_ID);
    }

    public String getFirstName() {
        return Optional.ofNullable(firstName).orElse(EMPTY_STRING);
    }

    public String getLastName() {
        return Optional.ofNullable(lastName).orElse(EMPTY_STRING);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barber)) return false;
        Barber barber = (Barber) o;
        return id == barber.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
