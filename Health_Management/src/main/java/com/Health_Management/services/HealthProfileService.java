package com.Health_Management.services;

import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.configs.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthProfileService {

    public List<HealthProfile> getAllProfiles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM HealthProfile", HealthProfile.class).list();
        }
    }

    public HealthProfile getLatestByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<HealthProfile> query = session.createQuery(
                "FROM HealthProfile WHERE user.id = :userId ORDER BY id DESC", HealthProfile.class);
            query.setParameter("userId", userId);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    public void saveOrUpdate(HealthProfile profile) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(profile);
            tx.commit();
        }
    }
}
