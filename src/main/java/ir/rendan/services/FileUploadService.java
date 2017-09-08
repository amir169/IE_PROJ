package ir.rendan.services;

import ir.rendan.model.DataFile;
import ir.rendan.repository.DataFileRepository;
import ir.rendan.util.ConstantReader;
import ir.rendan.util.StringGenerator;
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
    private final DataFileRepository dataFileRepository;

    public FileUploadService(ConstantReader constants,DataFileRepository dataFileRepository) {
        this.constants = constants;
        this.dataFileRepository = dataFileRepository;
    }

    @PostMapping("api/files/upload-image")
    public ResponseEntity<String> publicFileUpload(@RequestParam("file") MultipartFile file) {
        JSONObject jObj = new JSONObject();
        try {

            DataFile dataFile = new DataFile(file.getOriginalFilename(),constants.getPublicFolderPath());

            dataFileRepository.save(dataFile);

            Files.createDirectories(Paths.get(constants.getPublicFolderPath()));
            Files.write(Paths.get(dataFile.getAddress()), file.getBytes());
            jObj.put("key" , dataFile.getKey());

    } catch (IOException e) {
        e.printStackTrace();
    }
        return new ResponseEntity<>(jObj.toString(),HttpStatus.OK);
    }

    @PostMapping("api/files/upload")
    public ResponseEntity<String> privateFileUpload(@RequestParam("file") MultipartFile file) {
        JSONObject jObj = new JSONObject();
        try {

            DataFile dataFile = new DataFile(file.getOriginalFilename(),constants.getPrivateFolderPath());

            dataFileRepository.save(dataFile);

            Files.createDirectories(Paths.get(constants.getPrivateFolderPath()));

//            if(file.getSize() > 100000)
//                return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);

            Files.write(Paths.get(dataFile.getAddress()), file.getBytes());
            jObj.put("key" , dataFile.getKey());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jObj.toString(),HttpStatus.OK);
    }

    @GET
    @Path("download/{file_key}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getFile(
            @PathParam("file_key") String key) {

        File file;

        DataFile dataFile = dataFileRepository.getByKey(key);

        try{
            file = new File(dataFile.getAddress());
        }catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(file).build();

    }

}
