package com.ksv.analyticaltool.controller;

import com.ksv.analyticaltool.model.dto.RequestDto;
import com.ksv.analyticaltool.model.dto.ResponseDto;
import com.ksv.analyticaltool.model.mapper.RequestMapper;
import com.ksv.analyticaltool.service.FileParserService;
import com.ksv.analyticaltool.service.FileService;
import com.ksv.analyticaltool.service.QueryService;
import com.ksv.analyticaltool.service.RequestService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class RequestController {
    private static final String FILE_READ_SUCCESS_MESSAGE = "File read successfully: ";
    private static final String FILE_READ_FAIL_MESSAGE = "Could not read the file: ";
    private static final String INCORRECT_FILE_DATA_MESSAGE = "Incorrect file data: ";
    private final FileService fileService;
    private final FileParserService fileParserService;
    private final QueryService queryService;
    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @GetMapping("/output")
    public List<ResponseDto> getResponse() {
        return queryService.getAverageWaitingTime(requestService.getAll().stream()
                .map(requestMapper::getRequestDtoFromRequest)
                .collect(Collectors.toList()));
    }

    @PostMapping("/input")
    public ResponseEntity<String> saveData(@RequestParam("file") MultipartFile file) {
        List<String> fileLines;
        List<RequestDto> requestDtoList;
        try {
            fileLines = fileService.readFile(file);
            requestDtoList = fileParserService.parseFileLines(fileLines);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(INCORRECT_FILE_DATA_MESSAGE + file.getOriginalFilename());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(FILE_READ_FAIL_MESSAGE + file.getOriginalFilename());
        }
        requestService.addAll(requestDtoList.stream()
                .map(requestMapper::getRequestFromRequestDto)
                .collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.OK)
                .body(FILE_READ_SUCCESS_MESSAGE + file.getOriginalFilename());
    }
}
