package com.techbisatest.blogapi.models;

public class BlogHistory {
    private int id;
    private int blogId;
    private String field;
    private String oldValue;
    private String newValue;
    private String timestamp;

    public BlogHistory(int id, int blogId, String field, String oldValue, String newValue, String timestamp) {
        this.id = id;
        this.blogId = blogId;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.timestamp = timestamp;
    }

    // Métodos Getter
    public int getId() { return id; }
    public int getBlogId() { return blogId; }
    public String getField() { return field; }
    public String getOldValue() { return oldValue; }
    public String getNewValue() { return newValue; }
    public String getTimestamp() { return timestamp; }
}
