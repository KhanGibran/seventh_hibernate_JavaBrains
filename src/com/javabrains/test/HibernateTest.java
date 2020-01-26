package com.javabrains.test;

import com.javabrains.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.Query;

import java.util.List;

public class HibernateTest
{
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("com/javabrains/hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        User user1 = (User)session.get(User.class,5);
//
//        User user2 = (User)session.get(User.class,5);

        Query query = session.createQuery("from User where userId = 1");
        query.setCacheable(true);
        List<User> users = query.list();

        session.getTransaction().commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
//        User user3 = (User)session2.get(User.class,5);
        Query query2 = session.createQuery("from User where userId = 1");
        query2.setCacheable(true);
        users = query2.list();
        session2.getTransaction().commit();
        session2.close();
    }
}
