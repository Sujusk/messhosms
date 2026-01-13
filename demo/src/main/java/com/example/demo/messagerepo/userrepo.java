package com.example.demo.messagerepo;

import com.example.demo.entity.users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface userrepo extends MongoRepository<users,String> {

}
