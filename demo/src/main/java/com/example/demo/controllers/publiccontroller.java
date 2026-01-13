//package com.example.demo.controllers;
//
//
//import com.example.demo.entity.users;
//import com.example.demo.messageservice.userservices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/public")
//public class publiccontroller {
//    @Autowired
//    private userservices userservices;
//
//
//    @PostMapping("/newuser")
//    public ResponseEntity<users> createuser(@RequestBody users user){
//        try{
//            userservices.save(user);
//            return new ResponseEntity<>(user, HttpStatus.CREATED);}
//        catch(Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}
