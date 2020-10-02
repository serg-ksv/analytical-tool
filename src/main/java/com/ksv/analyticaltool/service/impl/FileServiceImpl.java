package com.ksv.analyticaltool.service.impl;

import com.ksv.analyticaltool.service.FileService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    public List<String> readFile(MultipartFile file) throws RuntimeException {
        List<String> list;
        try (var reader = new BufferedReader(new InputStreamReader(file.getInputStream(),
                StandardCharsets.UTF_8))) {
            list = reader.lines().collect(Collectors.toList());
            return list;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
