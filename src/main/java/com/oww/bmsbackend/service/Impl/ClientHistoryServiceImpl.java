package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Barber;
import com.oww.bmsbackend.entity.ClientHistory;
import com.oww.bmsbackend.exception.NotAcceptableException;
import com.oww.bmsbackend.exception.NotFoundException;
import com.oww.bmsbackend.repository.BarberRepository;
import com.oww.bmsbackend.repository.ClientHistoryRepository;
import com.oww.bmsbackend.service.ClientHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientHistoryServiceImpl implements ClientHistoryService {

    private final ClientHistoryRepository clientHistoryRepository;
    private final BarberRepository barberRepository;

    public ClientHistoryServiceImpl(ClientHistoryRepository clientHistoryRepository,
                                    BarberRepository barberRepository) {
        this.clientHistoryRepository = clientHistoryRepository;
        this.barberRepository = barberRepository;
    }

    @Override
    public List<ClientHistory> findAll() {
        List<ClientHistory> clientHistories = clientHistoryRepository.findAll();
        if (clientHistories.isEmpty()) throw new NotFoundException("Not Found: Client histories is not found");
        return clientHistoryRepository.findAll();
    }

    @Override
    public ClientHistory findById(int id) {
        return clientHistoryRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Not Found: Client history by id = %d is not found", id)));
    }

    @Override
    public ClientHistory createClientHistory(ClientHistory clientHistory) {
        if (clientHistory.getId() != 0) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must be 0 to create client history this id = %d",
                            clientHistory.getId()));
        }
        return clientHistoryRepository.save(clientHistory);
    }

    @Override
    public ClientHistory updateClientHistory(ClientHistory clientHistory) {
        Optional<Barber> optionalBarber = barberRepository.findById(clientHistory.getBarber().getId());
        Optional<ClientHistory> optionalClientHistory = clientHistoryRepository.findById(clientHistory.getId());
        if (!optionalBarber.isPresent()) {
            throw new NotAcceptableException(
                    "Not Acceptable: Trying to update a client optionalClientHistory without a optionalBarber");
        }
        if (!optionalClientHistory.isPresent()) {
            throw new NotAcceptableException("Not Acceptable: Trying to update non-existent a client history");
        }
        if (clientHistory.getId() == 0) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must not be 0 to create client history this id = %d",
                            clientHistory.getId()));
        }
        return clientHistoryRepository.save(clientHistory);
    }

    @Override
    public void deleteById(int id) {
        Optional<ClientHistory> optionalClientHistory = clientHistoryRepository.findById(id);
        if (!optionalClientHistory.isPresent())
            throw new NotFoundException(String.format("Not Found: Client history by id = %d is not found", id));
        else
            clientHistoryRepository.deleteById(id);
    }
}
