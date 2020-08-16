package com.screencap.dictionary.models.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "notes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "dateTime")
    private String dateTime;

    @ManyToOne(
        cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
        })
    @JoinColumn(name = "user_id")

    private User user;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "page")
    private List<Page> pages;

    @Column(name = "sqlite_note_id")
    private Integer sqliteNoteId;


    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Note() {}

    public Note(String name, String dateTime, User user, List<Page> pages, Integer sqliteNoteId) {
        this.name = name;
        this.dateTime = dateTime;
        this.user = user;
        this.pages = pages;
        this.sqliteNoteId = sqliteNoteId;
    }



    public Integer getSqliteNoteId() {
        return this.sqliteNoteId;
    }

    public void setSqliteNoteId(Integer sqliteNoteId) {
        this.sqliteNoteId = sqliteNoteId;
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


    public List<Page> getPages() {
        return this.pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }


    public void addPage(Page page) {
        if (pages == null)
            pages = new ArrayList<>();

        page.setNote(this);
        pages.add(page);

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
            ", dateTime='" + getDateTime() + "'" +
            ", user='" + getUser() + "'" +
            ", pages='" + getPages() + "'" +
            ", sqliteNoteId='" + getSqliteNoteId() + "'" +
            "}";
    }


}
