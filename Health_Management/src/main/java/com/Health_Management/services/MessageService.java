package com.Health_Management.services;

import com.Health_Management.configs.HibernateUtil;
import com.Health_Management.pojo.Message;
import com.Health_Management.pojo.User;
import org.hibernate.Session;

import java.util.List;

public class MessageService {

    public void sendMessage(Message message) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
        }
    }

    public List<User> getExperts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE role = 'Expert'", User.class).list();
        }
    }

    public List<User> getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User WHERE role = 'User'", User.class).list();
        }
    }

    public List<Message> getConversationBetween(User u1, User u2) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Message WHERE (sender.userId = :u1 AND receiver.userId = :u2) " +
                         "OR (sender.userId = :u2 AND receiver.userId = :u1) ORDER BY sentTime ASC";
            return session.createQuery(hql, Message.class)
                    .setParameter("u1", u1.getUserId())
                    .setParameter("u2", u2.getUserId())
                    .list();
        }
    }
}
