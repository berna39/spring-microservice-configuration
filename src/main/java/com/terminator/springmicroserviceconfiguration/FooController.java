package com.terminator.springmicroserviceconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooController {

    @Value("${app.message}")
    private String message;

    @Value(("${app.description: the FooApp description}")) // If the placeholder doesn't exist, we need to set a default value
    private String description;

    @GetMapping("/")
    public String index(){
        return message;
    }

    @GetMapping("/description")
    public String description(){
        return description;
    }
}
