package com.screencap.dictionary.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resources")
public class Resources {

    @GetMapping
    public String getResources() {
        System.out.println("hello");
        return "Hello World 3";
    }
}
