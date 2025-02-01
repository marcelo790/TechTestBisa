package com.techbisatest.blogapi.utils;

import com.techbisatest.blogapi.models.Blog;
import com.techbisatest.blogapi.services.AuthorService0;

public class Errors {

    private static AuthorService0 authorService = new AuthorService0();
    public String addBlogError(Blog blog) {
        if(blog == null) {
            return "Blog no creado";
        }else if(blog.getTitle() == null || blog.getTitle().isEmpty()) {
            return "El titulo es obligatorio";
        }else if(blog.getTheme() == null || blog.getTheme().isEmpty()) {
            return "El tema es obligatorio";
        }else if(blog.getContent() == null || blog.getContent().isEmpty()) {
            return "El contenido es obligatorio";
        }else if(blog.getAuthor() == null){
            return "El autor es obligatorio";
        }
        return "";
    }
}
