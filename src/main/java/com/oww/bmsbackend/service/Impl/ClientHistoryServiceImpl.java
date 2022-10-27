package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.ClientHistory;
import com.oww.bmsbackend.repository.ClientHistoryRepository;
import com.oww.bmsbackend.service.ClientHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientHistoryServiceImpl implements ClientHistoryService {

    private final ClientHistoryRepository repository;

    public ClientHistoryServiceImpl(ClientHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ClientHistory> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public ClientHistory createClientHistory(ClientHistory clientHistory) {
        return repository.save(clientHistory);
    }

    @Override
    public ClientHistory updateClientHistory(ClientHistory clientHistory) {
        return repository.save(clientHistory);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
