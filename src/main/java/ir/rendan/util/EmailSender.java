package ir.rendan.util;

import com.google.common.collect.Lists;
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
public class EmailSender {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConstantReader constants;

    public void send(String subject,String body,String to) throws AddressException {

        final Email email;

        email = DefaultEmail.builder()
                .from(new InternetAddress(constants.getEmailAddress()))
                .to(Lists.newArrayList(new InternetAddress(to)))
                .subject(subject)
                .body(body)
                .build();

        System.out.println("hi");

        emailService.send(email);

    }
}
