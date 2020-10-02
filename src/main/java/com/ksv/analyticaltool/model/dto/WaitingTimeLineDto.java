package com.ksv.analyticaltool.model.dto;

import com.ksv.analyticaltool.model.ResponseType;
import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WaitingTimeLineDto {
    private String serviceId;
    private String questionTypeId;
    private ResponseType responseType;
    private LocalDate date;
    private int time;
}
