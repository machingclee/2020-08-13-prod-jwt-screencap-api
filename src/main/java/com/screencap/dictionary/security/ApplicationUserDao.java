package com.screencap.dictionary.security;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import com.screencap.dictionary.models.User;
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
        Session session = sessionFactory.getCurrentSession();
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



    @Override
    public User getUserByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();

        try {

            TypedQuery<User> query = session.createQuery("from User where username=:username", User.class);
            query.setParameter("username", username);
            List<User> users = query.getResultList();

            if (users.size() > 0)
                return users.get(0);
            else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tx.commit();
            session.close();
        }

        return null;
    }



    @Override
    public void saveUser(User user) {

        Session session = sessionFactory.getCurrentSession();
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
