package com.ksv.analyticaltool.model.dto;

import com.ksv.analyticaltool.model.ResponseType;
import java.time.LocalDate;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QueryLineDto {
    private String serviceId;
    private String questionTypeId;
    private ResponseType responseType;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
