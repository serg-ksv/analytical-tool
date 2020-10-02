package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.model.Request;
import com.ksv.analyticaltool.repository.RequestRepository;
import com.ksv.analyticaltool.service.RequestService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    @Override
    public List<Request> addAll(List<Request> requests) {
        return requestRepository.saveAll(requests);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }
}
