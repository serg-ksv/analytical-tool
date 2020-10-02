package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.model.dto.ResponseDto;
import com.ksv.analyticaltool.model.mapper.QueryLineMapper;
import com.ksv.analyticaltool.model.mapper.RequestDtoMapper;
import com.ksv.analyticaltool.model.mapper.WaitingTimeLineMapper;
import com.ksv.analyticaltool.service.FileParserService;
import com.ksv.analyticaltool.service.FileService;
import com.ksv.analyticaltool.service.QueryService;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryServiceImplTest {
    private static final String FILE_PATH = "src/test/resources/test-data.txt";
    private QueryService queryService;
    private FileService fileService;
    private FileParserService fileParserService;

    @BeforeEach
    public void setUp() {
        queryService = new QueryServiceImpl(
                new QueryLineMapper(), new WaitingTimeLineMapper());
        fileService = new FileServiceImpl();
        fileParserService = new FileParserServiceImpl(new RequestDtoMapper());
    }

    @SneakyThrows
    @Test
    public void getAverageWaitingTime() {
        var multipartFile = new MockMultipartFile("test-data.txt",
                new FileInputStream(new File(FILE_PATH)));
        var requestDtoList = fileParserService
                .parseFileLines(fileService.readFile(multipartFile));
        var actual = queryService.getAverageWaitingTime(requestDtoList);
        var expected = List.of(
                new ResponseDto("83"),
                new ResponseDto("100"),
                new ResponseDto("-"));
        assertEquals(expected, actual);
    }

}
