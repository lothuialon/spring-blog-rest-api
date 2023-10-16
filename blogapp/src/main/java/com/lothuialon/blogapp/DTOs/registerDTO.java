package com.lothuialon.blogapp.DTOs;
import java.util.Objects;

public class registerDTO {
    private String username;
    private String email;
    private String password;


    public registerDTO() {
    }

    public registerDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public registerDTO username(String username) {
        setUsername(username);
        return this;
    }

    public registerDTO email(String email) {
        setEmail(email);
        return this;
    }

    public registerDTO password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof registerDTO)) {
            return false;
        }
        registerDTO registerDTO = (registerDTO) o;
        return Objects.equals(username, registerDTO.username) && Objects.equals(email, registerDTO.email) && Objects.equals(password, registerDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password);
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
 
}
