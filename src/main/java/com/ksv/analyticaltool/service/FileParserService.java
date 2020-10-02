package com.ksv.analyticaltool.service;

import com.ksv.analyticaltool.model.dto.RequestDto;
import java.util.List;

public interface FileParserService {
    List<RequestDto> parseFileLines(List<String> fileLines);
}
