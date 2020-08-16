package com.screencap.dictionary.models.entities;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "vocabs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vocab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "_sqlite_pageId")
    private Integer pageId;

    @Column(name = "word")
    private String word;

    @Column(name = "pronounciation")
    private String pronounciation;

    @Column(name = "explanation", columnDefinition = "text")
    private String explaination;

    @ManyToOne(cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH
    })

    @JsonBackReference
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(name = "sqlite_page_id")
    private Integer sqlitePageId;

    @Column(name = "sqlite_vocab_id")
    private Integer sqliteVocabId;


    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public Vocab() {}


    public Vocab(
        String name, Integer pageId, String word, String pronounciation, String explaination, Page page, Integer sqlitePageId, Integer sqliteVocabId
    ) {
        this.name = name;
        this.pageId = pageId;
        this.word = word;
        this.pronounciation = pronounciation;
        this.explaination = explaination;
        this.page = page;
        this.sqlitePageId = sqlitePageId;
        this.sqliteVocabId = sqliteVocabId;
    }



    public String getExplaination() {
        return this.explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }



    public Integer getSqlitePageId() {
        return this.sqlitePageId;
    }

    public void setSqlitePageId(Integer sqlitePageId) {
        this.sqlitePageId = sqlitePageId;
    }



    public Integer getSqliteVocabId() {
        return this.sqliteVocabId;
    }

    public void setSqliteVocabId(Integer sqliteVocabId) {
        this.sqliteVocabId = sqliteVocabId;
    }



    public Integer getPageId() {
        return this.pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
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


    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }



    public LocalDateTime getCreateDateTime() {
        return this.createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return this.updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }



    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", pageId='" + getPageId() + "'" +
            ", word='" + getWord() + "'" +
            ", Prounciation='" + getPronounciation() + "'" +
            ", explaination='" + getExplaination() + "'" +
            ", page='" + getPage() + "'" +
            ", sqlitePageId='" + getSqlitePageId() + "'" +
            ", sqliteVocabId='" + getSqliteVocabId() + "'" +
            "}";
    }



}
