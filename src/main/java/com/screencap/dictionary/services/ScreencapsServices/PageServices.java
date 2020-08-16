package com.screencap.dictionary.services.ScreencapsServices;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import com.screencap.dictionary.models.dtos.PageDto;
import com.screencap.dictionary.models.entities.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageServices {
    @Autowired
    private SessionFactory sessionFactory;


    public void deletePageByUsernameAndNoteId(String username, Integer pageId) throws Exception {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {


            List<Page> pages = session
                .createQuery(
                    "from Page p " +
                        "where p.note.user.username=:username " +
                        "and p.id=:pageId",
                    Page.class
                )
                .setParameter("username", username)
                .setParameter("pageId", pageId)
                .getResultList();

            if (pages.size() == 0)
                throw new Exception("no result was found");

            for (Page page : pages) {
                page.getNote().setPages(null);
                session.delete(page);
            }

            tx.commit();



        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        } finally {
            session.close();
        }
    }

    public List<PageDto> getPagesByUsernameAndNoteId(
        String username,
        Integer noteId
    ) throws Exception {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Page> pages = new ArrayList<>();
        List<PageDto> pageDtos = new ArrayList<>();
        try {

            pages = session
                .createQuery(
                    "from Page p " +
                        "where p.note.user.username=:username " +
                        "and p.note.id=:noteId",
                    Page.class
                )
                .setParameter("username", username)
                .setParameter("noteId", noteId)
                .getResultList();

            for (Page page : pages) {
                pageDtos.add(
                    new PageDto(
                        page.getId(),
                        page.getName(),
                        page.getDateTime()
                    )
                );
            }

            tx.commit();


        } catch (Exception e) {
            throw new Exception();
        } finally {
            session.close();
        }


        return pageDtos;
    }



    public List<PageDto> getPagesByUsername(String username) throws Exception {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Page> pages = new ArrayList<>();
        List<PageDto> pageDtos = new ArrayList<>();
        try {

            TypedQuery<Page> query = session.createQuery(
                "from Page p " +
                    "where p.note.user.username=:username",
                Page.class
            );
            query.setParameter("username", username);
            pages = query.getResultList();

            for (Page page : pages) {
                pageDtos.add(
                    new PageDto(
                        page.getId(),
                        page.getName(),
                        page.getDateTime()
                    )
                );
            }

            tx.commit();


        } catch (Exception e) {
            throw new Exception();
        } finally {
            session.close();
        }

        return pageDtos;
    }
}

