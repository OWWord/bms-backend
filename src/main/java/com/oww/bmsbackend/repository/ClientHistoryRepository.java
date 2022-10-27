package com.oww.bmsbackend.repository;

import com.oww.bmsbackend.entity.ClientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientHistoryRepository extends JpaRepository<ClientHistory, Integer> {
}
