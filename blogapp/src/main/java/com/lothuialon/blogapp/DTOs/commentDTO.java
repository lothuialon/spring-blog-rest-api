package com.lothuialon.blogapp.DTOs;

import com.lothuialon.blogapp.entity.blogpost;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

public class commentDTO {

    private int id;

    private String name;

    private String email;

    private String body;

    public commentDTO() {
    }

    public commentDTO(int id, String name, String email, String body) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public commentDTO id(int id) {
        setId(id);
        return this;
    }

    public commentDTO name(String name) {
        setName(name);
        return this;
    }

    public commentDTO email(String email) {
        setEmail(email);
        return this;
    }

    public commentDTO body(String body) {
        setBody(body);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof commentDTO)) {
            return false;
        }
        commentDTO commentDTO = (commentDTO) o;
        return id == commentDTO.id && Objects.equals(name, commentDTO.name) && Objects.equals(email, commentDTO.email)
                && Objects.equals(body, commentDTO.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, body);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", body='" + getBody() + "'" +
                "}";
    }

}
