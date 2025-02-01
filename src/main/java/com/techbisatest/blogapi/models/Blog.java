package com.techbisatest.blogapi.models;
import java.util.ArrayList;
import java.util.List;

public class Blog {
    private int id;
    private String title;
    private String theme;
    private String content;
    private String periodicity;
    private boolean allowsComments;
    private Author author;
    private List<Comment> comments = new ArrayList<>();

    public Blog() {
    }

    public Blog(int id, String title, String theme, String content, String periodicity, boolean allowsComments, Author author) {
        this.id = id;
        this.title = title;
        this.theme = theme;
        this.content = content;
        this.periodicity = periodicity;
        this.allowsComments = allowsComments;
        this.author = author;
        this.comments = new ArrayList<>(); // Inicializa la lista de comentarios

        author.addBlog(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public boolean isAllowsComments() {
        return allowsComments;
    }

    public void setAllowsComments(boolean allowsComments) {
        this.allowsComments = allowsComments;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
