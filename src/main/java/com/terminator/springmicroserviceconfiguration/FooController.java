package com.terminator.springmicroserviceconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FooController {

    @Autowired
    private Database database;

    @Value("${app.message}")
    private String message;

    @Value(("${app.description: the FooApp description}")) // If the placeholder doesn't exist, we need to set a default value
    private String description;

    @Value("${app.akatsuki}")
    List<String> akatsuki;

    @Value("#{${db.connection}}") // this will evaluate what's between #{} as an expression (Spring Expression language)
    Map<String, String> dbConfig;

    @GetMapping("/")
    public String getMessage(){
        return message;
    }

    @GetMapping("/description")
    public String getDescription(){
        return description;
    }

    @GetMapping("/akatsuki")
    public List<String> getAkatsuki(){
        return akatsuki;
    }

    @GetMapping("/config")
    public Map<String, String> getConfig(){
        return dbConfig;
    }

    @GetMapping("/other-config")
    public String getConfigFromBean(){
        return String.format("connection %s, db_name: %s", database.getConnection(), database.getName());
    }
}
