package com.example.demo.messageservice;


import com.example.demo.entity.users;
import com.example.demo.messagerepo.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class userdetailserviceimpl implements UserDetailsService {

    @Autowired
    private userrepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Optional<users> user= userrepo.findById(userid);
        if(user.isPresent()){
            UserDetails Userdetails=org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUserid())
                    .password(user.get().getPassword())
                    .roles(user.get().getRoles().toArray(new String[0])).build();
        return  Userdetails;
        }
        throw  new UsernameNotFoundException("User not found :"+ userid);
    }
}
