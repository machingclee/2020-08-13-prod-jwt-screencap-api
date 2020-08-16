package com.screencap.dictionary.models.dtos;

import java.util.List;
import com.screencap.dictionary.models.entities.Note;
import com.screencap.dictionary.models.entities.Vocab;

public class PageDto {
    private Integer id;
    private String name;
    private String dateTime;
    private Note note;
    private List<Vocab> vocabs;

    public PageDto(Integer id, String name, String dateTime) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
    }

    public PageDto(Integer id, String name, String dateTime, Note note) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.note = note;
    }

    public PageDto(Integer id, String name, String dateTime, List<Vocab> vocabs) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.vocabs = vocabs;
    }


    public PageDto(Integer id, String name, String dateTime, Note note, List<Vocab> vocabs) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.note = note;
        this.vocabs = vocabs;
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

    public Note getNote() {
        return this.note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public List<Vocab> getVocabs() {
        return this.vocabs;
    }

    public void setVocabs(List<Vocab> vocabs) {
        this.vocabs = vocabs;
    }


}
