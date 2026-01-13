package com.example.demo.messageservice;


import com.example.demo.entity.users;
import com.example.demo.messagerepo.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Component
public class userservices {
    @Autowired
    private userrepo userrepo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public List<users> findAll(){
        return userrepo.findAll();
    }
    @Transactional
    public boolean save( users user){
        if (user.getUserid() == null || !user.getUserid().matches("\\d{10}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}

        try {
user.setPassword(passwordEncoder.encode(user.getPassword()));
user.setRoles(Arrays.asList("USER"));
            userrepo.save(user);
            return true;
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }
    public users findById(String id){
        return userrepo.findById(id).orElse(null);
    }  
    

}
