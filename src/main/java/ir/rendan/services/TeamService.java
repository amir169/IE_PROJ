package ir.rendan.services;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.multipart.MultipartFile;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Created by ABM on 5/17/2017.
 */
@Path("team")
public class TeamService {
    @Path("upload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
                               @FormDataParam("file") MultipartFile file,
                               @FormDataParam("file") FormDataContentDisposition fileDetail
    ) {

//        saveFile(file, "aFile");

        String fileDetails = "File saved at /Volumes/Drive2/temp/file/" + "aFile" + " with tags ";

        if(file==null) System.err.println("whyyyyy");
        if(fileDetail==null) System.err.println("sky high");

        return Response.ok(fileDetails).build();
    }

    private void saveFile(InputStream file, String name) {
        try {
			/* Change directory path */
            java.nio.file.Path path = FileSystems.getDefault().getPath("E:\\files\\" + name);
			/* Save InputStream as file */
            Files.copy(file, path);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    @Path("/get-code")
    @POST
    public Response getCode(){

        return Response.ok().build();

    }

    @Path("/select-code")
    @POST
    public Response selectCode(String name){
        /*Criteria cr = HibernateUtils.getSession().createCriteria(Code.class);
        cr.add(Restrictions.gt("name", name));
        */

        return Response.ok().build();

    }
}

