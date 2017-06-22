package ir.rendan.util;

import com.google.common.collect.Lists;
import ir.rendan.model.Team;
import ir.rendan.model.User;
import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by Amir Shams on 5/27/2017.
 */
@Component
public class EmailUtils {

    private final EmailService emailService;

    private final ConstantReader constants;

    public EmailUtils(EmailService emailService, ConstantReader constants) {
        this.emailService = emailService;
        this.constants = constants;
    }

    public void sendActivationMail(User user) throws AddressException {

        String link = "http://" + constants.getServerAddress() + ":" + constants.getServerPort() +"/api/user/validate/" + user.getActivationCode();
        link += "\n\n";
        link += "user: " + user.getUsername();
        link += "\npass: " + user.getPassword();

        send("Validation Link",link,user.getEmail());
    }

    public void sendTeamInvitationMail(User user, Team team) throws AddressException {

        String body = "http://" + constants.getServerAddress() + ":" + constants.getServerPort() +"/api/invitation/accept/" + team.getName();

        send("Team Invitation",body,user.getEmail());
    }

    private void send(String subject, String body, String to) throws AddressException {

        final Email email;

        email = DefaultEmail.builder()
                .from(new InternetAddress(constants.getEmailAddress()))
                .to(Lists.newArrayList(new InternetAddress(to)))
                .subject(subject)
                .body(body)
                .build();

        emailService.send(email);

    }
}
