package com.screencap.dictionary.models.dtos;

import java.util.List;
import com.screencap.dictionary.models.entities.User;

public class NoteDto {
    private Integer id;
    private String name;
    private String dateTime;
    private User user;
    private List<PageDto> pages;


    public NoteDto(Integer id, String name, String dateTime) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;

    }

    public NoteDto(Integer id, String name, String dateTime, User user) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.user = user;
    }

    public NoteDto(Integer id, String name, String dateTime, List<PageDto> pages) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.pages = pages;
    }

    public NoteDto(Integer id, String name, String dateTime, User user, List<PageDto> pages) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.user = user;
        this.pages = pages;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PageDto> getPages() {
        return this.pages;
    }

    public void setPages(List<PageDto> pages) {
        this.pages = pages;
    }

}
