package pd.workshop.springrestapiproject.exception;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SizeLimitExceededException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)

    public ResponseEntity<String> handleSizeLimitExceededException(SizeLimitExceededException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(" file size was exceeded that size was allowed" + ex.getMessage());
    }


   @ExceptionHandler(value = MultipartException.class)
   @ResponseStatus(value = HttpStatus.BAD_REQUEST)

    public ResponseEntity<String> handleMultipartException(MultipartException ex) {
       return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error occurred during file upload: " + ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
        return new ResponseEntity<>("File not found." + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return new ResponseEntity<>("Error occurred : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }






}
