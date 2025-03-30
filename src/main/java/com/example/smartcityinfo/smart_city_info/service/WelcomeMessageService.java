package com.example.smartcityinfo.smart_city_info.service;

import com.example.smartcityinfo.smart_city_info.WelcomeMessage;
import com.example.smartcityinfo.smart_city_info.domain.exception.WelcomeMessageException;
import org.springframework.stereotype.Service;

@Service
public class WelcomeMessageService {

    public WelcomeMessage createWelcomeMessage(String message) {
        if (message == null || message.isEmpty()) {
            throw new WelcomeMessageException("A mensagem de boas-vindas não pode ser vazia.");
        }

        return new WelcomeMessage(message);
    }
}
