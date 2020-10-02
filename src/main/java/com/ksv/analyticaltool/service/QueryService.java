package com.ksv.analyticaltool.service;

import com.ksv.analyticaltool.model.dto.RequestDto;
import com.ksv.analyticaltool.model.dto.ResponseDto;
import java.util.List;

public interface QueryService {
    List<ResponseDto> getAverageWaitingTime(List<RequestDto> requestDtoList);
}
