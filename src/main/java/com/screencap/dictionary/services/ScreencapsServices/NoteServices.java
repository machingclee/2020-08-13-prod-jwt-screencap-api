package com.screencap.dictionary.services.ScreencapsServices;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;
import com.screencap.dictionary.models.dtos.NoteDto;
import com.screencap.dictionary.models.entities.Note;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NoteServices {
    @Autowired
    private SessionFactory sessionFactory;



    public void deleteNoteByUsernameAndNoteId(String username, Integer noteId) throws Exception {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {


            List<Note> notes = session
                .createQuery(
                    "from Note n " +
                        "where n.user.username=:username " +
                        "and n.id=:noteId",
                    Note.class
                )
                .setParameter("username", username)
                .setParameter("noteId", noteId)
                .getResultList();


            for (Note note : notes) {
                note.getUser().setNotes(null);
                session.delete(note);
            }

            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        } finally {
            session.close();
        }


    }


    public List<NoteDto> getNotesByUsername(String username) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Note> notes = new ArrayList<>();
        List<NoteDto> noteDtos = new ArrayList<>();
        try {


            TypedQuery<Note> query = session.createQuery(
                "from Note n where n.user.username=:username",
                Note.class
            );
            query.setParameter("username", username);
            notes = query.getResultList();

            for (Note note : notes) {
                noteDtos.add(
                    new NoteDto(
                        note.getId(),
                        note.getName(),
                        note.getDateTime()
                    )
                );
            }

            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();
        }

        return noteDtos;
    }
}
