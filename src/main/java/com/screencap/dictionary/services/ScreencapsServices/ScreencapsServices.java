package com.screencap.dictionary.services.ScreencapsServices;

import java.util.List;
import com.screencap.dictionary.models.UploadVocabsRequestBody;
import com.screencap.dictionary.models.entities.Note;
import com.screencap.dictionary.models.entities.Page;
import com.screencap.dictionary.models.entities.User;
import com.screencap.dictionary.models.entities.Vocab;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreencapsServices {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public VocabService vocabService;

    @Autowired
    public NoteServices noteServices;

    @Autowired
    public PageServices pageServices;


    public ScreencapsServices() {}

    public void saveVocabs(String username, UploadVocabsRequestBody deserializedBody) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {


            List<Note> notes = deserializedBody.getNotes();
            List<Page> pages = deserializedBody.getPages();
            List<Vocab> vocabs = deserializedBody.getVocabs();
            User user = session
                .createQuery("from User where username=:username", User.class)
                .setParameter("username", username)
                .getResultList()
                .get(0);

            // ======================= save the sqliteDB structure of my desktop app =======================
            // save all entities into database, even they don't have relation setup in our hibernate model.
            // the relation will be setup after the transaction is committed according to their fields.

            for (Note note : notes) {


                // id from the source is id in the sqlite database
                note.setSqliteNoteId(note.getId());
                user.addNote(note);
                session.save(note);

            }

            notes = session
                .createQuery("from Note n where n.user.username=:username", Note.class)
                .setParameter("username", username)
                .getResultList();


            for (Page page : pages) {
                // id from the source is id in the sqlite database
                page.setSqlitePageId(page.getId());
                page.setSqliteNoteId(page.getNoteId());

                for (Note note : notes) {
                    if (note.getSqliteNoteId().equals(page.getSqliteNoteId())) {
                        note.addPage(page);
                    }
                }
                session.save(page);
            }

            pages = session
                .createQuery("from Page p where p.note.user.username=:username", Page.class)
                .setParameter("username", username)
                .getResultList();

            for (Vocab vocab : vocabs) {
                // id from the source is id in the sqlite database
                vocab.setSqliteVocabId(vocab.getId());
                vocab.setSqlitePageId(vocab.getPageId());


                for (Page page : pages) {
                    if (page.getSqlitePageId().equals(vocab.getSqlitePageId())) {
                        page.addVocab(vocab);
                    }
                }

                session.save(vocab);
            }

            // nothing needs to be saved, on commition their relations will be updated.
            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


    public void deleteAll(String username) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            String query = "select n from Note n where n.user.username=:username";
            List<Note> notes = session
                .createQuery(query, Note.class)
                .setParameter("username", username)
                .getResultList();

            for (Note note : notes) {
                note.getUser().setNotes(null);
                session.delete(note);
            }

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();
        }
    }
}
