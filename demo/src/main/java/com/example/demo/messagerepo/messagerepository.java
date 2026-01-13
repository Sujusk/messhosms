package com.example.demo.messagerepo;

import com.example.demo.entity.messageentity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface messagerepository extends MongoRepository<messageentity,String> {

}
