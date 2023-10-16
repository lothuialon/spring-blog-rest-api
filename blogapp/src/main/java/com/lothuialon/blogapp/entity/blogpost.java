package com.lothuialon.blogapp.entity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class blogpost {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name =  "description")
    private String description;
    
    @Column(name = "content")
    private String content;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private Set<comment> comments = new HashSet<>();

    public blogpost() {
    }

    public blogpost(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public blogpost(Integer id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public blogpost id(Integer id) {
        setId(id);
        return this;
    }

    public blogpost title(String title) {
        setTitle(title);
        return this;
    }

    public blogpost description(String description) {
        setDescription(description);
        return this;
    }

    public blogpost content(String content) {
        setContent(content);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof blogpost)) {
            return false;
        }
        blogpost blogpost = (blogpost) o;
        return Objects.equals(id, blogpost.id) && Objects.equals(title, blogpost.title) && Objects.equals(description, blogpost.description) && Objects.equals(content, blogpost.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, content);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
    

}
