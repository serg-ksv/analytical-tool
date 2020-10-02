package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.model.RequestType;
import com.ksv.analyticaltool.model.ResponseType;
import com.ksv.analyticaltool.model.dto.RequestDto;
import com.ksv.analyticaltool.model.mapper.RequestDtoMapper;
import com.ksv.analyticaltool.service.FileParserService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileParserServiceImplTest {
    private FileParserService fileParserService;

    @BeforeEach
    void setUp() {
        fileParserService = new FileParserServiceImpl(new RequestDtoMapper());
    }

    @Test
    public void parseFileLines() {
        var expected = List.of(new RequestDto()
                .setRequestType(RequestType.C)
                .setServiceId("1.1")
                .setQuestionTypeId("8.15.1")
                .setResponseType(ResponseType.P)
                .setDate(LocalDate.of(2012, 10, 15))
                .setDateFrom(LocalDate.of(2012, 10, 15))
                .setTime(83));
        var fileLines = List.of("7", "C 1.1 8.15.1 P 15.10.2012 83");
        var actual = fileParserService.parseFileLines(fileLines);
        assertEquals(expected, actual);
    }
}
