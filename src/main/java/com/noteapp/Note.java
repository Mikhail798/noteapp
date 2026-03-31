package com.noteapp;

public class Note {
    private String text;
    private String createdAt;

    public Note() {}

    public Note(String text, String createdAt) {
        this.text = text;
        this.createdAt = null;
    }

    public String getText() {
        return text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return createdAt + " | " + text;
    }
}
