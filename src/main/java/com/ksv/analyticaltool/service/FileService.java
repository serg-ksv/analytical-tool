package com.ksv.analyticaltool.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    List<String> readFile(MultipartFile file);
}
