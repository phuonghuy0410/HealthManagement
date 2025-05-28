/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Health_Management.Repository;

import com.Health_Management.pojo.HealthProfile;
import com.Health_Management.configs.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HealthProfileRepository {
    public boolean saveOrUpdateHealthProfile(HealthProfile profile) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(profile);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public HealthProfile getByUserId(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM HealthProfile WHERE user.id = :uid", HealthProfile.class)
                    .setParameter("uid", userId)
                    .uniqueResult();
        }
    }

    public List<HealthProfile> getAllProfiles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM HealthProfile", HealthProfile.class).list();
        }
    }
}