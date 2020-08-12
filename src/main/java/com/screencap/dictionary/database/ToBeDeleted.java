package com.screencap.dictionary.database;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ToBeDeleted {
    @Value("${DB_PASSWORD}")
    private String DB_PASSWORD;

    @Autowired
    private org.springframework.core.env.Environment env;

    public ToBeDeleted() {

    }

    @PostConstruct
    public void print() {
        System.out.println("\n\npassword:" + env.getProperty("DB_PASSWORD") + "\n\n");
    }
}
