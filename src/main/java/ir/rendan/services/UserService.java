package ir.rendan.services;
import ir.rendan.model.User;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.dto.RegistrationDTO;
import ir.rendan.services.dto.UserLightDTO;
import ir.rendan.util.ConstantReader;
import ir.rendan.util.EmailUtils;
import ir.rendan.util.MessageTranslator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;


/**
 * Created by Amir Shams on 5/20/2017.
 */
@Path("api/user")
@Component
public class UserService{

    private final EmailUtils emailUtils;
    private final UserRepository userRepository;
    private final ConstantReader constants;
    private final MessageTranslator translator;

    public UserService(UserRepository userRepository, EmailUtils emailUtils, ConstantReader constants, MessageTranslator translator) {
        this.userRepository = userRepository;
        this.emailUtils = emailUtils;
        this.constants = constants;
        this.translator = translator;
    }

    @GET
    @Path("exists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userExists(@QueryParam("email") String email)
    {
        User user = userRepository.findByEmail(email);
        if(user == null)
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.login.failed")).build();
        return Response.ok(UserLightDTO.loadFrom(user)).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegistrationDTO dto)
    {
        if(!validateDTO(dto))
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.register.incomplete")).build();

        if(userRepository.exists(dto.getUsername()))
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.register.username_exists")).build();

        if(userRepository.findByEmail(dto.getEmail()) != null)
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.register.email_exists")).build();

        User user = new User(dto.getUsername(),dto.getPassword(),dto.getEmail());

        try {
            userRepository.save(user);
        }catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("system.error")).build();
        }

        try {
            emailUtils.sendActivationMail(user);
        } catch (Exception e) {
            userRepository.delete(user);
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.email.invalid")).build();
        }

        return Response.ok(translator.translate("user.validation.sent")).build();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password)
    {

        User user = userRepository.findOne(username);
        if(user == null || !user.getPassword().equals(password))
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.login.failed")).build();

        if(user.getEnabled() == 0)
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("user.account.not_activated")).build();


        loginUser(user);
        return Response.ok(translator.translate("user.login.successful")).build();
    }

    @GET
    @Path("validate/{code}")
    public Response validate(@PathParam("code") String code)
    {
        User user = userRepository.findByActivationCode(code);

        if(user == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        user.validate();

        userRepository.save(user);

        loginUser(user);

        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Response.ok(translator.translate("user.account.activated")).build();
    }


    private boolean validateDTO(RegistrationDTO dto) {
        return (dto.getUsername() != null || dto.getUsername().isEmpty()) &&
                (dto.getPassword() != null || dto.getPassword().isEmpty()) &&
                (dto.getEmail() != null || dto.getEmail().isEmpty());
    }

    private void loginUser(User user){

        List<GrantedAuthority> grantedAuths = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        Authentication a =  new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), grantedAuths);
        SecurityContextHolder.getContext().setAuthentication(a);

    }

    @GET
    @Path("whoami")
    public Response profile()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findOne(username);

        return Response.ok(UserLightDTO.loadFrom(user)).build();
    }

}
