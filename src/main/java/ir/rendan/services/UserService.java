package ir.rendan.services;
import com.google.common.collect.Lists;
import ir.rendan.model.User;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.base.AbstractService;
import ir.rendan.services.dto.RegistrationDTO;
import ir.rendan.util.EmailSender;
import ir.rendan.util.StringGenerator;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Amir Shams on 5/20/2017.
 */
@Path("api/user")
public class UserService extends AbstractService{

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @GET
    @Path("test_user")
    public Response userExists(@QueryParam("username") String username)
    {
        //TODO check if user exists
        return Response.ok().build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegistrationDTO dto)
    {
        if(!validateDTO(dto))
            return Response.status(Response.Status.BAD_REQUEST).entity(translate("user.register.incomplete")).build();

        if(userRepository.exists(dto.getUsername()))
            return Response.status(Response.Status.BAD_REQUEST).entity(translate("user.register.username_exists")).build();

        User user = new User();
        user.setEnabled(new Short("0"));
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setActivationCode(StringGenerator.generateValidationCode());

        try {
            userRepository.save(user);
        }catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(translate("user.register.email_exists")).build();
        }

        try {
            String link = "http://" + constants.getServerAddress() + ":" + constants.getServerPort() +"/api/user/validate/" + user.getActivationCode();
            emailSender.send("Validation Link",link,user.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e);
            userRepository.delete(user);
            return Response.status(Response.Status.BAD_REQUEST).entity(translate("user.email.invalid")).build();
        }

        return Response.ok(translate("user.validation.sent")).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password)
    {

        User user = userRepository.findOne(username);
        if(user == null || !user.getPassword().equals(password))
            return Response.status(Response.Status.BAD_REQUEST).entity(translate("user.login.failed")).build();

        if(user.getEnabled() == 0)
            return Response.status(Response.Status.BAD_REQUEST).entity(translate("user.account.not_activated")).build();


        loginUser(username,password);
        return Response.ok(translate("user.login.successful")).build();
    }

    @GET
    @Path("validate/{code}")
    public Response validate(@PathParam("code") String code)
    {
        User user = userRepository.findByActivationCode(code);

        if(user == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        user.setActivationCode(null);
        user.setEnabled(new Short("1"));

        userRepository.save(user);

        loginUser(user.getUsername(),user.getPassword());

        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Response.ok(translate("user.account.activated")).build();
    }


    private boolean validateDTO(RegistrationDTO dto) {
        return (dto.getUsername() != null || dto.getUsername().isEmpty()) &&
                (dto.getPassword() != null || dto.getPassword().isEmpty()) &&
                (dto.getEmail() != null || dto.getEmail().isEmpty());
    }

    private void loginUser(String username,String password){

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        Authentication a =  new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(a);

    }
    
}
