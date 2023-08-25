package pd.workshop.springrestapiproject.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pd.workshop.springrestapiproject.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {


        String name = file.getOriginalFilename();


        String filepath = path + File.separator + name;


        File createFolder = new File(path);
        if(!createFolder.exists())
        {
            createFolder.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filepath));
        return name;
    }
}



