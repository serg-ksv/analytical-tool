package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.model.dto.RequestDto;
import com.ksv.analyticaltool.model.mapper.RequestDtoMapper;
import com.ksv.analyticaltool.service.FileParserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FileParserServiceImpl implements FileParserService {
    private final RequestDtoMapper mapper;

    @Override
    public List<RequestDto> parseFileLines(List<String> fileLines) {
        return fileLines
                .stream()
                .skip(1)
                .map(mapper::getRequestDto)
                .collect(Collectors.toList());
    }
}
