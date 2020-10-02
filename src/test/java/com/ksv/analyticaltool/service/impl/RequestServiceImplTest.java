package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.model.Request;
import com.ksv.analyticaltool.model.RequestType;
import com.ksv.analyticaltool.model.ResponseType;
import com.ksv.analyticaltool.repository.RequestRepository;
import com.ksv.analyticaltool.service.RequestService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestServiceImplTest {
    private RequestService requestService;
    private RequestRepository repository;
    private Request request;
    private Request savedRequest;

    @BeforeEach
    void setUp() {
        repository = mock(RequestRepository.class);
        requestService = new RequestServiceImpl(repository);
        request = new Request()
                .setRequestType(RequestType.C)
                .setServiceId("1")
                .setQuestionTypeId("2")
                .setResponseType(ResponseType.N)
                .setDate(LocalDate.EPOCH)
                .setDateFrom(LocalDate.MIN)
                .setDateTo(LocalDate.MAX)
                .setTime(80);
        savedRequest = new Request()
                .setId(1L)
                .setRequestType(RequestType.C)
                .setServiceId("1")
                .setQuestionTypeId("2")
                .setResponseType(ResponseType.N)
                .setDate(LocalDate.EPOCH)
                .setDateFrom(LocalDate.MIN)
                .setDateTo(LocalDate.MAX)
                .setTime(80);
    }

    @Test
    void addAll() {
        when(repository.saveAll(List.of(request))).thenReturn(List.of(savedRequest));
        assertEquals(List.of(savedRequest), requestService.addAll(List.of(request)));
    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(savedRequest));
        assertEquals(List.of(savedRequest), requestService.getAll());
    }
}
