package com.andylibrian.pulau.core;

import org.hibernate.validator.constraints.Length;

public class Message {

    private long id;

    @Length(max = 3)
    private String content;

    public Message() {
        // Jackson deserialization
    }

    public Message(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
