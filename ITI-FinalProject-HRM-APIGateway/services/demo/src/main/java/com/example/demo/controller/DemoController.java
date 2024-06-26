package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

        @GetMapping
        public String hello() {
            return "Hello from demo service";
        }

        @PostMapping
        public String bye() {
            return "Bye from demo service";
        }
}
