package com.ksv.analyticaltool.model.mapper;

import com.ksv.analyticaltool.model.dto.QueryLineDto;
import com.ksv.analyticaltool.model.dto.RequestDto;
import org.springframework.stereotype.Component;

@Component
public class QueryLineMapper {
    public QueryLineDto getQueryLineDto(RequestDto dto) {
        return new QueryLineDto()
                .setServiceId(dto.getServiceId())
                .setQuestionTypeId(dto.getQuestionTypeId())
                .setResponseType(dto.getResponseType())
                .setDateFrom(dto.getDateFrom())
                .setDateTo(dto.getDateTo());
    }
}
