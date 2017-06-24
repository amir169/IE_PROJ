package ir.rendan.services;

import ir.rendan.util.ConstantReader;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Amir Shams on 5/18/2017.
 */
@Controller
@Path("api/files")
public class FileUploadService {

    private final ConstantReader constants;

    public FileUploadService(ConstantReader constants) {
        this.constants = constants;
    }

    @PostMapping("api/files/upload-image")

    public ResponseEntity<String> singleImageUpload(@RequestParam("file") MultipartFile file) {
        JSONObject jObj = new JSONObject();
        try {

            System.out.println(file.getName());
//        Files.createDirectories(Paths.get(constants.getFolderPath()));
        Files.write(Paths.get(file.getOriginalFilename()), file.getBytes());

            jObj.put("data" , file.getOriginalFilename());
    } catch (IOException e) {
        e.printStackTrace();
    }
        return new ResponseEntity<>(jObj.toString(),HttpStatus.OK);
    }

    @PostMapping("api/files/upload-code")
    public ResponseEntity<String> singleCodeUpload(@RequestParam("file") MultipartFile file) {
        JSONObject jObj = new JSONObject();
        try {

//        Files.createDirectories(Paths.get(constants.getFolderPath()));
            Files.write(Paths.get(file.getOriginalFilename()), file.getBytes());
            jObj.put("data" , file.getOriginalFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(jObj.toString(),HttpStatus.OK);
    }

    @GET
    @Path("download/{file_name}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(
            @PathParam("file_name") String fileName) {

        return Response.ok().entity(new File(fileName)).build();

    }

}
