package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@Data
public class messageentity {
    @Id
    private String number;
    @NonNull
    private String message;
    private LocalDateTime time;


}
