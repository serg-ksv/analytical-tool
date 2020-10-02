package com.ksv.analyticaltool.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RequestType requestType;
    private String serviceId;
    private String questionTypeId;
    private ResponseType responseType;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private LocalDate date;
    private Integer time;
}
