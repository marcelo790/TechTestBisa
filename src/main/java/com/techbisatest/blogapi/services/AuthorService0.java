package com.techbisatest.blogapi.services;

import com.techbisatest.blogapi.models.*;

import java.util.ArrayList;
import java.util.List;

public class AuthorService0 {
    private List<Author> authors = new ArrayList<>();
    private List<Blog> blogs = new ArrayList<>();

    public AuthorService0() {
        authors.add(new Author(1, "Juan", "Pérez", "Carlos", "1980-05-10", "España", "juan.perez@email.com"));
        authors.add(new Author(2, "María", "Gómez", "Ana", "1990-07-12", "México", "maria.gomez@email.com"));
    }

    public Author findAuthorById(int authorId) {
        return authors.stream()
                .filter(a -> a.getId() == authorId)
                .findFirst()
                .orElse(null);
    }

    // Validar si el autor existe
    public String validateAuthorForBlog(Blog blog) {
        Author author = findAuthorById(blog.getAuthor().getId());
        if (author == null) {
            return "Autor no encontrado";
        }
        return "";  // Si el autor existe
    }

    // Metodo para registrar un autor si no existe
    public Author getOrCreateAuthor(Author author) {
        Author existingAuthor = findAuthorById(author.getId());
        if (existingAuthor != null) {
            return existingAuthor;  // El autor ya existe
        } else {
            authors.add(author);  // Crear nuevo autor si no existe
            return author;
        }
    }

    public void addBlog(Blog blog) {
        blogs.add(blog);
    }


    // Agregar un nuevo autor
    public void addAuthor(Author author) {
        authors.add(author);
    }
}
