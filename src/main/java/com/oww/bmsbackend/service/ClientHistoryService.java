package com.oww.bmsbackend.service;

import com.oww.bmsbackend.entity.ClientHistory;

import java.util.List;
import java.util.Optional;

public interface ClientHistoryService {

    List<ClientHistory> findAll();

    Optional<ClientHistory> findById(int id);

    ClientHistory createClientHistory(ClientHistory clientHistory);

    ClientHistory updateClientHistory(ClientHistory clientHistory);

    void deleteById(int id);

}
