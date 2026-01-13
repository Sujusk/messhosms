package com.example.demo.controllers;


import com.example.demo.entity.messageentity;
import com.example.demo.entity.users;
import com.example.demo.messageservice.messageservice;
import com.example.demo.messageservice.userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class messagecontrollers {





 @Autowired
 private messageservice messageservice;
@Autowired
private userservices userservices;


@GetMapping("{userid}")
public ResponseEntity<?> getallmessages(@PathVariable String userid){
    users user = userservices.findById(userid);
    List<messageentity> Messageentity= user.getMessages();
    if(Messageentity!=null && !Messageentity.isEmpty()){
        return new ResponseEntity<>(Messageentity,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

}
@PostMapping("{userid}")
public ResponseEntity<messageentity> postmessages(@RequestBody messageentity messages, @PathVariable String userid){
    try{
        messages.setTime(LocalDateTime.now());
   messageservice.save(messages,userid);
    return new ResponseEntity<>(messages,HttpStatus.CREATED);}
    catch(Exception e){
        System.out.println(e);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
@GetMapping("id/{number}")
 public ResponseEntity<messageentity> getmessage(@PathVariable String number){
 Optional<messageentity> Messageentity= Optional.ofNullable(messageservice.findById(number));
    return Messageentity.map(messageentity -> new ResponseEntity<>(messageentity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

}
