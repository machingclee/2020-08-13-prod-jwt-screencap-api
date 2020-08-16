package com.screencap.dictionary.models.dtos;

import com.screencap.dictionary.models.entities.Page;

public class VocabDto {
    private Integer id;
    private String word;
    private String pronounciation;
    private String explanation;
    private Page page;



    public VocabDto(Integer id, String word, String pronounciation, String explanation) {
        this.id = id;
        this.word = word;
        this.pronounciation = pronounciation;
        this.explanation = explanation;
    }


    public VocabDto(Integer id, String word, String pronounciation, String explanation, Page page) {
        this.id = id;
        this.word = word;
        this.pronounciation = pronounciation;
        this.explanation = explanation;
        this.page = page;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronounciation() {
        return this.pronounciation;
    }

    public void setPronounciation(String pronounciation) {
        this.pronounciation = pronounciation;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
