package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class first {
@GetMapping("abc")
public String fun(){
    return "fun";
}
}
