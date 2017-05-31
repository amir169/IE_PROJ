package ir.rendan.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by Amir Shams on 5/29/2017.
 */
@Component
public class MessageTranslator {

    private final MessageSource messageSource;

    public MessageTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(String expression)
    {
        return messageSource.getMessage(expression,new Object[0],new Locale("fa"));
    }
}
