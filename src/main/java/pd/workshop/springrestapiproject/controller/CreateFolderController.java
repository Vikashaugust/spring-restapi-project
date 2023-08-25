package pd.workshop.springrestapiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pd.workshop.springrestapiproject.helper.FileUploadHelper;
import pd.workshop.springrestapiproject.playload.FileResponse;
import pd.workshop.springrestapiproject.service.FileService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/filemanager")
public class CreateFolderController {

    private FileService fileService;

    @Autowired
    public CreateFolderController(FileService fileService) {
        this.fileService = fileService;
    }

    @Value("${project.image}")
       private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileupload(@RequestParam("image") MultipartFile image)
        {
        String fileName = null;
        try {
            fileName = this.fileService.uploadImage(path,image);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse("File was not inserted","Image is not uploaded successfully due to some error !!"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new FileResponse(fileName,"Image is successfully uploaded !!"),HttpStatus.OK);
    }

    // Rename Folder

    @PutMapping("/renamefolder")
    public String renameFolder(@RequestParam("old_path") String oldPath,
                               @RequestParam("new_name") String newName) {
        try {
            File oldFolder = new File(oldPath);


            String parentPath = oldFolder.getParent();


            File newFolder = new File(parentPath, newName);


            boolean success = oldFolder.renameTo(newFolder);

            if (success) {
                return "Folder renamed successfully";
            } else {
                return "Failed to rename the folder";
            }
        } catch (Exception e) {
            return "Error while renaming the folder";
        }
    }

    // Delete Folder
    @DeleteMapping("/{folderName}")
    public ResponseEntity<String> deleteFolder(@PathVariable String folderName) {
        File folder = new File(folderName);

        if (!folder.exists()) {
            return new ResponseEntity<>("Folder does not exist.", HttpStatus.NOT_FOUND);
        }

        if (!folder.isDirectory()) {
            return new ResponseEntity<>("Not a folder.", HttpStatus.BAD_REQUEST);
        }

        boolean success = deleteRecursive(folder);
        if (success) {
            return new ResponseEntity<>("Folder deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete folder.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean deleteRecursive(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteRecursive(child);
                }
            }
        }
        return file.delete();
    }

}
