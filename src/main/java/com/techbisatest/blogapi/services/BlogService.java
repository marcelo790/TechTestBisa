package com.techbisatest.blogapi.services;

import com.techbisatest.blogapi.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogService {
    private List<Blog> blogs = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private Map<Integer, List<Comment>> comentarios = new HashMap<>();
    private int blogIdCounter = 1;
    private int commentsIdCounter = 1;
    private int authorIdCounter = 1;


    private List<BlogHistory> history = new ArrayList<>();
    private int historyIdCounter = 1;
    public List<Blog> getAllBlogs() {
        return blogs;
    }
    // Metodo para encontrar un blog por su ID
    public Blog findBlogById(int blogId) {
        for (Blog blog : blogs) {
            if (blog.getId() == blogId) {
                return blog;  // Devolver el blog si lo encuentra
            }
        }
        return null;  // Si no lo encuentra, devolver null
    }

    // Agregar un nuevo blog
        public void addBlog(Blog blog) {
        blog.setId(blogIdCounter++); // Asigna un ID único
        if(blog.getAuthor().getId() == 0){
            blog.getAuthor().setId(authorIdCounter++);
        }
        authors.add(blog.getAuthor());
        blogs.add(blog);
    }

    public void addComment(int blogId, Comment comentario) {
        if (!comentarios.containsKey(blogId)) {
            comentarios.put(blogId, new ArrayList<>());
        }
        comentario.setId(commentsIdCounter++);
        comentarios.get(blogId).add(comentario);
    }

    public List<Comment> getCommentsByBlogId(int blogId) {
        return comentarios.getOrDefault(blogId, new ArrayList<>());
    }

    public void updateBlog(int blogId, String newTitle, String newContent, boolean allowsComments) {
        Blog blog = findBlogById(blogId);
        if (blog == null) return;

        if (!blog.getTitle().equals(newTitle)) {
            addHistory(blogId, "title", blog.getTitle(), newTitle);
            blog.setTitle(newTitle);
        }

        if (!blog.getContent().equals(newContent)) {
            addHistory(blogId, "content", blog.getContent(), newContent);
            blog.setContent(newContent);
        }

        if (blog.isAllowsComments() != allowsComments) {
            addHistory(blogId, "allowsComments", String.valueOf(blog.isAllowsComments()), String.valueOf(allowsComments));
            blog.setAllowsComments(allowsComments);
        }
    }

    public void addHistory(int blogId, String field, String oldValue, String newValue) {
        String timestamp = java.time.LocalDateTime.now().toString();
        history.add(new BlogHistory(historyIdCounter++, blogId, field, oldValue, newValue, timestamp));
    }

    public List<BlogHistory> getBlogHistory(int blogId) {
        return history.stream().filter(h -> h.getBlogId() == blogId).toList();
    }
}
