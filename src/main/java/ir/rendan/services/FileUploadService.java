package ir.rendan.services;

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
    @PostMapping("api/files/upload")
    public ResponseEntity singleFileUpload(@RequestParam("file") MultipartFile file) {

        try {

        Files.write(Paths.get("F:/temp/" + file.getOriginalFilename()), file.getBytes());

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

        return Response.ok().entity(new File("F:/temp/" + fileName)).build();

    }

}
