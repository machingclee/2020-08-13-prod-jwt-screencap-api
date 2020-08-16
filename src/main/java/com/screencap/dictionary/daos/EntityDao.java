package com.screencap.dictionary.daos;


import com.screencap.dictionary.interfaces.IEntityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;


public class EntityDao<T> implements IEntityDao<T> {
    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public void save(T object) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }
    }


    @Override
    public void update(T object) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();


        try {
            session.update(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }

    }


    @Override
    public void delete(T object) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.delete(object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }

    }



}
