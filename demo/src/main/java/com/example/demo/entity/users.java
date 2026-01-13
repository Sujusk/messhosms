package com.example.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class users {
    @Id
    private String userid;
    @NonNull
    private String password;
    @DBRef
    private List<messageentity> messages=new ArrayList<>();

    private List<String> roles;
    public List<messageentity> getMessages() {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        return this.messages;
    }
}
