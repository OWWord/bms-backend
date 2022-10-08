package com.oww.bmsbackend.service;

import com.oww.bmsbackend.entity.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {

    List<Request> findAll();

    Optional<Request> findById(int id);

    Request createRequest(Request request);

    Request updateRequest(Request request);

    void deleteById(int id);

}
