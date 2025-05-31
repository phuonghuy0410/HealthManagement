package com.Health_Management.services;

import com.Health_Management.configs.HibernateUtil;
import com.Health_Management.pojo.Reminder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    public List<Reminder> getRemindersByUser(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Reminder r WHERE r.user.userId = :uid ORDER BY r.reminderTime ASC", Reminder.class)
                    .setParameter("uid", userId)
                    .list();
        }
    }

    public List<Reminder> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Reminder ORDER BY reminderTime ASC", Reminder.class).list();
        }
    }

    public void addOrUpdateReminder(Reminder reminder) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(reminder);
            tx.commit();
        }
    }

    public void deleteReminder(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Reminder r = session.get(Reminder.class, id);
            if (r != null) session.delete(r);
            tx.commit();
        }
    }
}
