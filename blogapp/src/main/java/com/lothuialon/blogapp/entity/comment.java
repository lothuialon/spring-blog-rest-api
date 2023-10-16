package com.lothuialon.blogapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class comment {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blogpost_id", nullable = false)
    private blogpost post;


    public comment() {
    }

    public comment(int id, String name, String email, String body, blogpost post) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
        this.post = post;
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

    public blogpost getPost() {
        return this.post;
    }

    public void setPost(blogpost post) {
        this.post = post;
    }

    public comment id(int id) {
        setId(id);
        return this;
    }

    public comment name(String name) {
        setName(name);
        return this;
    }

    public comment email(String email) {
        setEmail(email);
        return this;
    }

    public comment body(String body) {
        setBody(body);
        return this;
    }

    public comment post(blogpost post) {
        setPost(post);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof comment)) {
            return false;
        }
        comment comment = (comment) o;
        return id == comment.id && Objects.equals(name, comment.name) && Objects.equals(email, comment.email) && Objects.equals(body, comment.body) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, body, post);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", body='" + getBody() + "'" +
            ", post='" + getPost() + "'" +
            "}";
    }
    
}
