package com.techbisatest.blogapi.models;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String birthDate; // Formato YYYY-MM-DD
    private String country;
    private String email;
    private List<Blog> blogs;  // Lista de blogs del autor


    public Author() {}

    public Author(String firstName, String lastName, String middleName, String birthDate, String country, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.country = country;
        this.email = email;
        this.blogs = new ArrayList<>();
    }


    public Author(int id, String firstName, String lastName, String middleName, String birthDate, String country, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.country = country;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos para acceder y modificar la lista de blogs
    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public void addBlog(Blog blog) {
        this.blogs.add(blog);
    }
}
