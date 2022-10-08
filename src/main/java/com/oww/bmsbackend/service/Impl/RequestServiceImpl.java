package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Request;
import com.oww.bmsbackend.repository.RequestRepository;
import com.oww.bmsbackend.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;

    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Request> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Request> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Request createRequest(Request request) {
        return repository.save(request);
    }

    @Override
    public Request updateRequest(Request request) {
        return repository.save(request);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
