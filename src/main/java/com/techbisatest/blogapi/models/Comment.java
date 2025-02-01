package com.techbisatest.blogapi.models;

public class Comment {
    private int id;
    private String name;
    private String email;
    private String country;
    private int rating; // Puntuación entre 0 y 10
    private String content;
    private int blogId;  // Relación con el blog por ID

    public Comment() {}

    public Comment(String name, String email, String country, int rating, String content, int blogId) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.rating = rating;
        this.content = content;
        this.blogId = blogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}