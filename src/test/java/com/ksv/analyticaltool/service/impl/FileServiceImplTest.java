package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileServiceImplTest {
    private static final String FILE_PATH = "src/test/resources/test-data.txt";
    private FileService fileService;


    @BeforeEach
    void setUp() {
        fileService = new FileServiceImpl();
    }

    @SneakyThrows
    @Test
    public void readFile() {
        var expected = List.of("7",
                "C 1.1 8.15.1 P 15.10.2012 83",
                "C 1 10.1 P 01.12.2012 65",
                "C 1.1 5.5.1 P 01.11.2012 117",
                "D 1.1 8 P 01.01.2012-01.12.2012",
                "C 3 10.2 N 02.10.2012 100",
                "D 1 * P 8.10.2012-20.11.2012",
                "D 3 10 P 01.12.2012");
        var multipartFile = new MockMultipartFile("test-data.txt",
                new FileInputStream(new File(FILE_PATH)));
        var actual = fileService.readFile(multipartFile);
        assertEquals(expected, actual);
    }
}
