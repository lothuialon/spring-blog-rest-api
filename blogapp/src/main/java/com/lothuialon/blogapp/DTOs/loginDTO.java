package com.lothuialon.blogapp.DTOs;
import java.util.Objects;

public class loginDTO {
    private String usernameOrEmail;
    private String password;

    public loginDTO() {
    }

    public loginDTO(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return this.usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public loginDTO usernameOrEmail(String usernameOrEmail) {
        setUsernameOrEmail(usernameOrEmail);
        return this;
    }

    public loginDTO password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof loginDTO)) {
            return false;
        }
        loginDTO loginDTO = (loginDTO) o;
        return Objects.equals(usernameOrEmail, loginDTO.usernameOrEmail) && Objects.equals(password, loginDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernameOrEmail, password);
    }

    @Override
    public String toString() {
        return "{" +
            " usernameOrEmail='" + getUsernameOrEmail() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }
    
}
