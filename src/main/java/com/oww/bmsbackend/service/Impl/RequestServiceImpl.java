package com.oww.bmsbackend.service.Impl;

import com.oww.bmsbackend.entity.Request;
import com.oww.bmsbackend.exception.NotAcceptableException;
import com.oww.bmsbackend.exception.NotFoundException;
import com.oww.bmsbackend.repository.RequestRepository;
import com.oww.bmsbackend.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> findAll() {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) throw new NotFoundException("Not Found: Requests is not found");
        return requestRepository.findAll();
    }

    @Override
    public Request findById(int id) {
        return requestRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Not Found: Request by id = %d is not found", id)));
    }

    @Override
    public Request createRequest(Request request) {
        if (request.getId() != 0) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must be 0 to create request this id = %d", request.getId()));
        }
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequest(Request request) {
        Optional<Request> optionalRequest = requestRepository.findById(request.getId());
        if (!optionalRequest.isPresent()) {
            throw new NotAcceptableException("Not Acceptable: Trying to update non-existent a request");
        }
        if (request.getId() == 0) {
            throw new NotAcceptableException(
                    String.format("Not Acceptable: ID must not be 0 to create request this id = %d", request.getId()));
        }
        return requestRepository.save(request);
    }

    @Override
    public void deleteById(int id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (!optionalRequest.isPresent())
            throw new NotFoundException(String.format("Not Found: Request by id = %d is not found", id));
        else
            requestRepository.deleteById(id);
    }
}
