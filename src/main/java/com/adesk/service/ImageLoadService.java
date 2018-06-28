package com.adesk.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public interface ImageLoadService {
    public void load(MultipartFile file, String path) throws IOException;
    public void delete(File file);
}
