package com.example.demo.messageservice;

import com.example.demo.entity.messageentity;
import com.example.demo.entity.users;
import com.example.demo.messagerepo.messagerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class messageservice {
@Autowired
private messagerepository messagerepository;
@Autowired
private userservices userservices;
@Autowired
private sendmessages sendmessages;
@Autowired
private blockeduserservice blockeduserservice;

@Transactional
public void save(messageentity message, String userid){

    if (blockeduserservice.blockeduserservice(userid)) {
    System.out.println("Blocked user");
        return;
    }


    try {
       users user = userservices.findById(userid);

message.setTime(LocalDateTime.now());
sendmessages.sendmessage(message.getMessage(),user.getUserid());
 messageentity saved=messagerepository.save(message);
 user.getMessages().add(saved);
 userservices.save(user);}
   catch(Exception e){
       System.out.println(e);
       throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
public List<messageentity> findAll(){
    return messagerepository.findAll();
}
public messageentity findById(String id){
    return messagerepository.findById(id).orElse(null);
}

}
