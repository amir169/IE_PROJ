package ir.rendan.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Amir Shams on 5/18/2017.
 */
@Controller
public class FileUploadService {
    @PostMapping("/api/upload") // //new annotation since 4.3
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file) {

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get("F://temp//" + file.getOriginalFilename());
            Files.write(path, bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
