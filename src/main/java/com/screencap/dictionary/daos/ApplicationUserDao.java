package com.screencap.dictionary.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import com.screencap.dictionary.interfaces.IApplicationUserDao;
import com.screencap.dictionary.models.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDao implements IApplicationUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUsers() {

        List<User> users = new ArrayList<User>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            TypedQuery<User> query = session.createQuery("from User", User.class);
            users = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }

        return users;
    }


    public void updateUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.update(user);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }
    }



    @Override
    public User getUserByUserName(String username) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<User> users = new ArrayList<>();

        try {

            TypedQuery<User> query = session.createQuery("from User where username=:username", User.class);
            query.setParameter("username", username);
            users = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }

        if (users.size() > 0)
            return users.get(0);

        return null;
    }



    @Override
    public void saveUser(User user) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            session.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }
    }
}
