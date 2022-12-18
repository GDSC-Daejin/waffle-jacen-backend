package org.jacen.todo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "auth")
public class Auth {

    private String id;
    private String username;
    private String password;
    private String email;
    private String refreshToken;

    public Auth(String id, String username, String password, String email, String refreshToken) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.refreshToken = refreshToken;
    }

    public Auth() {
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getRole() {
        return this.refreshToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String setId(String id) {
        return this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
