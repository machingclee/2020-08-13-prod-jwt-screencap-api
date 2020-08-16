package com.screencap.dictionary.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.screencap.dictionary.models.entities.Note;
import com.screencap.dictionary.models.entities.Page;
import com.screencap.dictionary.models.entities.Vocab;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadVocabsRequestBody {
    private List<Note> notes;
    private List<Page> pages;
    private List<Vocab> vocabs;

    public UploadVocabsRequestBody() {}

    public UploadVocabsRequestBody(List<Note> notes, List<Page> pages, List<Vocab> vocabs) {
        this.notes = notes;
        this.pages = pages;
        this.vocabs = vocabs;
    }

    public List<Note> getNotes() {
        return this.notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Page> getPages() {
        return this.pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public List<Vocab> getVocabs() {
        return this.vocabs;
    }

    public void setVocabs(List<Vocab> vocabs) {
        this.vocabs = vocabs;
    }


    @Override
    public String toString() {
        return "{" +
            " notes='" + getNotes() + "'" +
            ", pages='" + getPages() + "'" +
            ", vocabs='" + getVocabs() + "'" +
            "}";
    }


}
