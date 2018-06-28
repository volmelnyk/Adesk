package com.adesk.service.implementation;

import com.adesk.service.ImageLoadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageLoadServiceImpl implements ImageLoadService {


    @Override
    public void load(MultipartFile file, String path) throws IOException {
        String newFile = path+file.getOriginalFilename();
        file.transferTo(new File(newFile));
    }

    @Override
    public void delete(File file) {

    }
}
