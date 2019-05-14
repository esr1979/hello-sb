package com.firstsb.hellosb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Value("${messages.home:default-value}")
    private String message;

    @RequestMapping("/")
    public String homePage(Model model) {
        log.info("Spring Boot with Thymeleaf example");
        model.addAttribute("message", message);
        return "home";
    }
}