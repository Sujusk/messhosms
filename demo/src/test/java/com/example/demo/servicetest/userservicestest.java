package com.example.demo.servicetest;

import com.example.demo.entity.users;
import com.example.demo.messagerepo.userrepo;
import com.example.demo.messageservice.userservices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class userservicestest {

    @Autowired
    private userrepo userrepo;
    @Autowired
    private userservices userservices;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Test
    public void findAlltest(){
        assertNotNull(userrepo.findAll());
    }
    @ParameterizedTest
    @ArgumentsSource(userargumentprovider.class)
    public void savetest(users user){
        assertTrue(userservices.save(user));
    }

    @ParameterizedTest
    @CsvSource({
            "950","898","977824"
    })
    public void findByIdtest(String id){
        assertNotNull(userrepo.findById(id));
    }



}
