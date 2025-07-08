package org.banking.pet.service;

import org.banking.pet.exception.ErrorCode;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private final MessageSource messageSource;

    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, key, locale);
    }

    public String getMessage(ErrorCode code, Locale locale) {
        return getMessage(code.getKey(), null, locale);
    }
}
