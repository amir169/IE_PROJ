package ir.rendan.services;



import ir.rendan.dao.CommentDAO;
import ir.rendan.dao.GenericDAO;
import ir.rendan.model.Comment;
import ir.rendan.model.UserInfo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


import java.util.List;

/**
 * Created by ABM on 5/18/2017.
 */
@Path("user")
public class UserService {
    @Path("/submit-comment")
    @POST
    public Response submitComment(String comment){
        System.out.println(comment);

        // find the user
        //

        // create comment and user
        UserInfo userInfo = new UserInfo();
        ir.rendan.model.Comment comment1 = new ir.rendan.model.Comment(comment , null , userInfo);

        // save comment
        GenericDAO genericDAO = new GenericDAO();
        genericDAO.insert(comment1);

        return Response.ok().build();
    }

    @Path("/get-comment")
    @POST
    public Response getComment(){
        CommentDAO commentDAO = new CommentDAO();
        List<Comment> comments = commentDAO.getAllComment();

        return Response.ok().entity(comments).build();

    }
}
