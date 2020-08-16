package com.screencap.dictionary.services.ScreencapsServices;

import java.util.ArrayList;
import java.util.List;
import com.screencap.dictionary.models.UploadVocabsRequestBody;
import com.screencap.dictionary.models.dtos.VocabDto;
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
public class VocabService {
    @Autowired
    private SessionFactory sessionFactory;



    public List<VocabDto> getVocabsByUsername(String username) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<VocabDto> vocabDtos = new ArrayList<>();

        try {

            List<Vocab> vocabs = session
                .createQuery("from Vocab v where v.page.note.user.username=:username", Vocab.class)
                .setParameter("username", username)
                .getResultList();

            for (Vocab vocab : vocabs) {
                vocabDtos.add(
                    new VocabDto(
                        vocab.getId(),
                        vocab.getWord(),
                        vocab.getPronounciation(),
                        vocab.getExplaination()
                    )
                );
            }

            tx.commit();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            session.close();
        }

        return vocabDtos;
    }



    public List<VocabDto> getVocabsByUsernameAndPageId(
        String username,
        Integer pageId
    ) throws Exception {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<VocabDto> vocabDtos = new ArrayList<>();

        try {

            List<Vocab> vocabs = session
                .createQuery(
                    "from Vocab v " +
                        "where v.page.note.user.username=:username " +
                        "and v.page.id=:pageId",
                    Vocab.class
                )
                .setParameter("username", username)
                .setParameter("pageId", pageId)
                .getResultList();

            for (Vocab vocab : vocabs) {
                vocabDtos.add(
                    new VocabDto(
                        vocab.getId(),
                        vocab.getWord(),
                        vocab.getPronounciation(),
                        vocab.getExplaination()
                    )
                );
            }

            tx.commit();


        } catch (Exception e) {
            throw new Exception();
        } finally {

            session.close();
        }

        return vocabDtos;
    }



}
