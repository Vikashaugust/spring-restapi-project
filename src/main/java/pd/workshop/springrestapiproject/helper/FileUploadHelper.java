package pd.workshop.springrestapiproject.helper;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Component
public class FileUploadHelper {
   public final String UPLOAD_DIR="C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image";


    public FileUploadHelper() throws IOException {
    }

    public boolean uploadFile(MultipartFile multipartFile)
    {
        boolean isUploaded = false;
        try
        {
            Files.copy(multipartFile.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            isUploaded = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return isUploaded;
    }
}




