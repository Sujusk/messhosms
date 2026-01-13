package com.example.demo.entity;

public class MessageRequest {
        private String number;
        private String content;


        public MessageRequest(String number, String content) {
            this.number = number;
            this.content = content;
        }

        // Getters and Setters
        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }

