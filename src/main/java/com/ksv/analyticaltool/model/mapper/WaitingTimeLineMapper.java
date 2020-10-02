package com.ksv.analyticaltool.model.mapper;

import com.ksv.analyticaltool.model.dto.RequestDto;
import com.ksv.analyticaltool.model.dto.WaitingTimeLineDto;
import org.springframework.stereotype.Component;

@Component
public class WaitingTimeLineMapper {
    public WaitingTimeLineDto getWaitingTimeLineDto(RequestDto dto) {
        return new WaitingTimeLineDto()
                .setServiceId(dto.getServiceId())
                .setQuestionTypeId(dto.getQuestionTypeId())
                .setResponseType(dto.getResponseType())
                .setDate(dto.getDate())
                .setTime(dto.getTime());
    }
}
