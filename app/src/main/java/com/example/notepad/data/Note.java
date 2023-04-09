package com.example.notepad.data;

import java.time.LocalDateTime;

public class Note {
    private Integer id;
    private String header; // заголовок
    private String text; //текст заметки
    private LocalDateTime timeCreate; // время и дата создания

    public Note() {
    }

    public Note(Integer id, String header, String text, LocalDateTime timeCreate) {
        this.id = id;
        this.header = header;
        this.text = text;
        this.timeCreate = timeCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", timeCreate=" + timeCreate +
                '}';
    }
}
