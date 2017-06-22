package ir.rendan.services;

import ir.rendan.util.ConstantReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    @PostMapping("api/files/upload")
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file) {

        try {

//        Files.createDirectories(Paths.get(constants.getFolderPath()));
        Files.write(Paths.get(file.getOriginalFilename()), file.getBytes());

    } catch (IOException e) {
        e.printStackTrace();
    }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GET
    @Path("download/{file_name}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(
            @PathParam("file_name") String fileName) {

        return Response.ok().entity(new File(fileName)).build();

    }

}
