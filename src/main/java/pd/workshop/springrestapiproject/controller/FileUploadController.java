package pd.workshop.springrestapiproject.controller;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pd.workshop.springrestapiproject.helper.FileUploadHelper;
import pd.workshop.springrestapiproject.playload.FileResponse;

import javax.naming.SizeLimitExceededException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequestMapping("/api/filemanager")
@RestController
public class FileUploadController {

    private FileUploadHelper fileUploadHelper;

    @Autowired

    public FileUploadController(FileUploadHelper fileUploadHelper) {
        this.fileUploadHelper = fileUploadHelper;
    }


    @PostMapping("/uploadfile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println((file.getContentType()));
        System.out.println(file.getName());

        try {
            if ((file.isEmpty())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
            }

           if (!file.getContentType().equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" only JPEG content are allowed ");
            }


            boolean isUploaded = fileUploadHelper.uploadFile(file);
            if (isUploaded) {
                return ResponseEntity.ok("File was uploaded Sucessfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong ! Try again");
    }


    //DELETING FILE---------------------------------------------------------------
    @DeleteMapping("/delete/{filename}")
    public String deleteFile(@PathVariable("filename") String filename) throws Exception{

        String filePath = "C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\" + filename;
        try {
            // Delete the file
            FileUtils.forceDelete(new File(filePath));
            return "File deleted successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to delete the file.";
        }
    }





    // Rename File
    @PutMapping("/rename")
    public static String renameFile(@RequestParam String filename, @RequestParam String name) {
        File file = new File("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\" + filename);
        boolean hasRename = file.renameTo(new File("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\" + name));
        if (hasRename) {
            return "File rename successful";
        } else {
            return "File rename failed";
        }
    }


    // Move File
    @PutMapping("/move")
    public static String moveFile(@RequestParam String filename) {
        File file = new File("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\" + filename);
        boolean move = file.renameTo(new File("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\Move\\" + filename));
        if (move == true) {
            return "File is moved successful!";
        } else {
            return "File is failed to move!";
        }
    }

    // Copy File

    @PutMapping("/copy")
    public static String copyfile(@RequestParam String filename) throws IOException {

        File source = new File("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\" + filename);
        File dest = new File("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\Move\\" + filename);


        FileSystemUtils.copyRecursively(source, dest);


        return "file copied successfully";
    }

    // Paste File

    @PostMapping("/paste")
    public String pasteFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "File is empty";
        }

        try {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());


            Path uploadDir = Paths.get("C:\\Users\\agarw\\OneDrive\\Desktop\\spring_project\\spring-restapi-project\\src\\main\\resources\\static\\image\\paste");
            Path filePath = uploadDir.resolve(fileName);


            Files.copy(file.getInputStream(), filePath);

            return "File pasted successfully";
        } catch (Exception e) {
            return "Error while pasting the file";
        }


    }


    // Cut File
    @PostMapping("/cut-file")
    public ResponseEntity<String> cutFile(@RequestParam("filePath") String filePath,
                                          @RequestParam("length") long length) {
        try {
            Path sourcePath = Paths.get(filePath);
            Path targetPath = Paths.get(filePath + ".cut");

            try (RandomAccessFile sourceFile = new RandomAccessFile(sourcePath.toFile(), "r");
                 RandomAccessFile targetFile = new RandomAccessFile(targetPath.toFile(), "rw")) {

                byte[] buffer = new byte[(int) length];
                sourceFile.readFully(buffer);

                targetFile.write(buffer);
            }

            return ResponseEntity.ok("File cut successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error cutting file");
        }
    }

}



