package com.ksv.analyticaltool.model.mapper;

import com.ksv.analyticaltool.model.Request;
import com.ksv.analyticaltool.model.dto.RequestDto;
import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    public Request getRequestFromRequestDto(RequestDto dto) {
        return new Request()
                .setRequestType(dto.getRequestType())
                .setServiceId(dto.getServiceId())
                .setQuestionTypeId(dto.getQuestionTypeId())
                .setResponseType(dto.getResponseType())
                .setDateFrom(dto.getDateFrom())
                .setDateTo(dto.getDateTo())
                .setDate(dto.getDate())
                .setTime(dto.getTime());
    }

    public RequestDto getRequestDtoFromRequest(Request request) {
        return new RequestDto()
                .setId(request.getId())
                .setRequestType(request.getRequestType())
                .setServiceId(request.getServiceId())
                .setQuestionTypeId(request.getQuestionTypeId())
                .setResponseType(request.getResponseType())
                .setDateFrom(request.getDateFrom())
                .setDateTo(request.getDateTo())
                .setDate(request.getDate())
                .setTime(request.getTime());
    }
}
