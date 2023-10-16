package com.lothuialon.blogapp.DTOs;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class postDTO {
//data transfer object

    private Integer id;

    @NotEmpty
    @Size(min = 4, message = "Title can't be smaller than 4 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Description can't be smaller than 10 characters")
    private String description;

    @NotEmpty
    @Size(min = 15, message = "Content can't be smaller than 15 characters")
    private String content;

    public postDTO() {
    }

    public postDTO(Integer id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public postDTO id(Integer id) {
        setId(id);
        return this;
    }

    public postDTO title(String title) {
        setTitle(title);
        return this;
    }

    public postDTO description(String description) {
        setDescription(description);
        return this;
    }

    public postDTO content(String content) {
        setContent(content);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof postDTO)) {
            return false;
        }
        postDTO postDTO = (postDTO) o;
        return Objects.equals(id, postDTO.id) && Objects.equals(title, postDTO.title) && Objects.equals(description, postDTO.description) && Objects.equals(content, postDTO.content);
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