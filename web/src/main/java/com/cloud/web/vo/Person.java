package com.cloud.web.vo;

import javax.validation.constraints.NotBlank;

public class Person {
    long id;
    @NotBlank
    String username;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
