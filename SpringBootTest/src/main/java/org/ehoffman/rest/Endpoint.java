package org.ehoffman.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Endpoint {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}