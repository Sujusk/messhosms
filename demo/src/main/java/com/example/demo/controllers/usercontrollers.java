package com.example.demo.controllers;


import com.example.demo.entity.users;
import com.example.demo.messageservice.userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class usercontrollers {


    @Autowired
    private userservices userservices;


    @GetMapping
    public ResponseEntity<?> getallusers(){
        List<users> Users= userservices.findAll();
        if(Users!=null && !Users.isEmpty()){
            return new ResponseEntity<>(Users,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping
    public ResponseEntity<users> createuser(@RequestBody users user){
        try{ userservices.save(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);}
        catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("id/{number}")
    public ResponseEntity<users> getuserdetail(@PathVariable String number){
        Optional<users> User= Optional.ofNullable(userservices.findById(number));
        return User.map(users -> new ResponseEntity<>(users, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
