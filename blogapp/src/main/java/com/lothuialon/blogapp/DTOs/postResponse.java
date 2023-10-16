package com.lothuialon.blogapp.DTOs;

import java.util.List;
import java.util.Objects;

public class postResponse {
    private List<postDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean isLast;


    public postResponse() {
    }

    public postResponse(List<postDTO> content, int pageNo, int pageSize, int totalElements, int totalPages, boolean isLast) {
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public List<postDTO> getContent() {
        return this.content;
    }

    public void setContent(List<postDTO> content) {
        this.content = content;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalElements() {
        return this.totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isIsLast() {
        return this.isLast;
    }

    public boolean getIsLast() {
        return this.isLast;
    }

    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    public postResponse content(List<postDTO> content) {
        setContent(content);
        return this;
    }

    public postResponse pageNo(int pageNo) {
        setPageNo(pageNo);
        return this;
    }

    public postResponse pageSize(int pageSize) {
        setPageSize(pageSize);
        return this;
    }

    public postResponse totalElements(int totalElements) {
        setTotalElements(totalElements);
        return this;
    }

    public postResponse totalPages(int totalPages) {
        setTotalPages(totalPages);
        return this;
    }

    public postResponse isLast(boolean isLast) {
        setIsLast(isLast);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof postResponse)) {
            return false;
        }
        postResponse postResponse = (postResponse) o;
        return Objects.equals(content, postResponse.content) && pageNo == postResponse.pageNo && pageSize == postResponse.pageSize && totalElements == postResponse.totalElements && totalPages == postResponse.totalPages && isLast == postResponse.isLast;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, pageNo, pageSize, totalElements, totalPages, isLast);
    }

    @Override
    public String toString() {
        return "{" +
            " content='" + getContent() + "'" +
            ", pageNo='" + getPageNo() + "'" +
            ", pageSize='" + getPageSize() + "'" +
            ", totalElements='" + getTotalElements() + "'" +
            ", totalPages='" + getTotalPages() + "'" +
            ", isLast='" + isIsLast() + "'" +
            "}";
    }
    
}
