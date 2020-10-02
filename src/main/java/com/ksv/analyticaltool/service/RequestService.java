package com.ksv.analyticaltool.service;

import com.ksv.analyticaltool.model.Request;
import java.util.List;

public interface RequestService {
    List<Request> addAll(List<Request> requests);

    List<Request> getAll();
}
