package com.ssolab.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String main(Authentication authentication){
        String authenticationData = authentication.toString();

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head><title>Hello</title></head>");
        htmlBuilder.append("<body><h1>Hello</h1><p>").append(authenticationData).append("</p></body>");
        htmlBuilder.append("</html>");
        String html = htmlBuilder.toString();

        return html;
    }
}
