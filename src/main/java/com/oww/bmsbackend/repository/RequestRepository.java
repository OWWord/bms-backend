package com.oww.bmsbackend.repository;

import com.oww.bmsbackend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
