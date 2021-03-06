package ir.rendan.services.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by Amir Shams on 5/24/2017.
 */
public abstract class AbstractService {
    @Autowired
    protected MessageSource messageSource;

    protected String translate(String message)
    {
        return messageSource.getMessage(message,new Object[0],new Locale("fa"));
    }
}
