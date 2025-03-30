package com.example.smartcityinfo.smart_city_info.controller;

import com.example.smartcityinfo.smart_city_info.WelcomeMessage;
import com.example.smartcityinfo.smart_city_info.service.WelcomeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/welcome")
public class WelcomeController {

    @Autowired
    private WelcomeMessageService welcomeMessageService;

    @PostMapping
    public WelcomeMessage createWelcomeMessage(@RequestBody String message) {
        return welcomeMessageService.createWelcomeMessage(message);
    }
}
