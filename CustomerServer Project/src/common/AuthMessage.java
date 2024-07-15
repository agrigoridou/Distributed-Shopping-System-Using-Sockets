package common;

import java.io.Serializable;

public class AuthMessage implements Serializable {
    private String username;
    private String password;

    public AuthMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}