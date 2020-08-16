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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "pages")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "_sqlite_noteId")
    private Integer noteId;

    @Column(name = "name")
    private String name;

    @Column(name = "date_time")
    private String dateTime;

    @JsonBackReference
    @ManyToOne(cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.REFRESH
    })
    @JoinColumn(name = "note_id")
    private Note note;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "vocab")
    private List<Vocab> vocabs;

    @Column(name = "sqlite_noteid")
    private Integer sqliteNoteId;

    @Column(name = "sqlite_page_id")
    private Integer sqlitePageId;


    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;



    public Page() {}



    public Page(Integer noteId, String name, String dateTime, Note note, List<Vocab> vocabs, Integer sqliteNoteId, Integer sqlitePageId) {

        this.noteId = noteId;
        this.name = name;
        this.dateTime = dateTime;
        this.note = note;
        this.vocabs = vocabs;
        this.sqliteNoteId = sqliteNoteId;
        this.sqlitePageId = sqlitePageId;
    }



    public Integer getSqliteNoteId() {
        return this.sqliteNoteId;
    }

    public void setSqliteNoteId(Integer sqliteNoteId) {
        this.sqliteNoteId = sqliteNoteId;
    }



    public Integer getSqlitePageId() {
        return this.sqlitePageId;
    }

    public void setSqlitePageId(Integer sqlitePageId) {
        this.sqlitePageId = sqlitePageId;
    }



    public List<Vocab> getVocabs() {
        return this.vocabs;
    }

    public void setVocabs(List<Vocab> vocabs) {
        this.vocabs = vocabs;
    }

    public Integer getNoteId() {
        return this.noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
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



    public void addVocab(Vocab vocab) {
        if (vocabs == null)
            vocabs = new ArrayList<>();

        vocab.setPage(this);
        vocabs.add(vocab);
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
            ", noteId='" + getNoteId() + "'" +
            ", name='" + getName() + "'" +
            ", dateTime='" + getDateTime() + "'" +
            ", note='" + getNote() + "'" +
            ", vocabs='" + getVocabs() + "'" +
            ", sqliteNoteId='" + getSqliteNoteId() + "'" +
            ", sqlitePageId='" + getSqlitePageId() + "'" +
            "}";
    }



}
